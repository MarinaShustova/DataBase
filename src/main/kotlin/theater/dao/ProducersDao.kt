package theater.dao

import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource
import theater.model.*

class ProducersDao(private val dataSource: DataSource) {
    fun createProducer(toCreate: Producer): Long {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO producers (employee_id, activity) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, toCreate.employee.id)
        stmt.setString(2, toCreate.activity)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun deleteProducer(id: Long) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM producers WHERE producers.id = ?")
        stmt.setInt(1, id.toInt())
        stmt.executeUpdate()
    }

    fun updateProducer(id: Long, keysNValues: Map<String, String>) { //updating in two statements
        val employeeProperties = keysNValues.asSequence().filter {
            it.key.equals("fio") || it.key.equals("sex") || it.key.equals("origin")
                    || it.key.equals("birth_date") || it.key.equals("hire_date")
                    || it.key.equals("salary") || it.key.equals("children_amount")
        }
        val producerProperties = keysNValues.asSequence().filter { it.key.equals("activity") }

        var questionMarks = employeeProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET ${questionMarks} WHERE " +
                        "employees.id = (SELECT employee_id FROM producers WHERE producers.id = ?)")

        var count = 1

        for (it in employeeProperties) {
            when (it.key) {
                "fio", "sex" -> {
                    stmt.setString(count++, it.value)
                }
                "birth_date", "hire_date" -> {
                    stmt.setDate(count++, Date.valueOf(it.value))
                }
                "salary", "children_amount", "origin" -> {
                    stmt.setInt(count++, it.value.toInt())
                }
            }
        }
        stmt.setLong(employeeProperties.toList().size + 1, id)
        stmt.executeUpdate()

        questionMarks = producerProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        stmt = dataSource.connection.prepareStatement(
                "UPDATE producers SET ${questionMarks} WHERE producers.id = ?")

        count = 1
        for (it in producerProperties) {
            when (it.key) {
                "activity" -> {
                    stmt.setString(count++, it.value)
                }
            }
        }
        stmt.setLong(producerProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }


    fun getProducerById(id: Long): Producer? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM producers WHERE id = ?"
        )
        stmt.setLong(1, id)
        val res = stmt.executeQuery()

        return if (res.next()) {
            val employeesDao = EmployeesDao(dataSource)
            val relatedEmployee = employeesDao.getEmployeeById(res.getLong("employee_id"))
            return if (relatedEmployee == null) {
                null
            } else {
                Producer(
                        res.getLong("id"),
                        relatedEmployee,
                        res.getString("activity")
                )
            }
        } else {
            null
        }
    }

    fun getProducerByName(fio: String): Producer? {
        val employeesDao = EmployeesDao(dataSource)
        val relatedEmployee = employeesDao.getEmployeeByName(fio)
        return if (relatedEmployee != null) {

            val stmt = dataSource.connection.prepareStatement(
                    "SELECT * FROM producers WHERE producers.employee_id = ?")
            stmt.setLong(1, relatedEmployee.id)
            val res = stmt.executeQuery()

            return if (res.next()) {
                Producer(
                        res.getLong("id"),
                        relatedEmployee,
                        res.getString("activity")
                )
            } else {
                null
            }
        } else {
            null
        }
    }

    fun getProducersBySex(sex: String): List<Producer?> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT producers.id as producer_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, activity " +
                        "FROM ((SELECT * FROM employees WHERE employees.sex = ?) e " +
                        "JOIN producers ON producers.employee_id = e.id)"
        )


        stmt.setString(1, sex)
        val res = stmt.executeQuery()

        var resultList = ArrayList<Producer?>()

        while (res.next()) {
            val countryDao = CountryDao(dataSource)
            val employeeCountry = countryDao.getCountry(res.getInt("origin"))
            if (employeeCountry == null) continue

            val relatedEmployee = Employee(res.getLong("employee_id"),
                    res.getString("fio"),
                    res.getString("sex"),
                    res.getDate("birth_date"),
                    res.getInt("children_amount"),
                    res.getInt("salary"),
                    employeeCountry.name,
                    res.getDate("hire_date")
            )

            resultList.add(Producer(
                    res.getLong("producer_id"),
                    relatedEmployee,
                    res.getString("activity") ))
        }

        return resultList
    }


}