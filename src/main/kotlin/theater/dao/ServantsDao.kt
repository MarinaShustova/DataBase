package theater.dao

import theater.model.Servant
import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource
import theater.model.*

class ServantsDao(private val dataSource: DataSource) {
    fun createServant(toCreate: Servant): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO servants (employee_id, activity) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, toCreate.employee.id)
        stmt.setString(2, toCreate.activity)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun deleteServant(id: Int) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM servants WHERE servants.id = ?")
        stmt.setInt(1, id.toInt())
        stmt.executeUpdate()
    }

    fun updateServant(id: Int, keysNValues: Map<String, String>) { //updating in two statements
        val employeeProperties = keysNValues.asSequence().filter {
            it.key.equals("fio") || it.key.equals("sex") || it.key.equals("origin")
                    || it.key.equals("birth_date") ||  it.key.equals("hire_date")
                    || it.key.equals("salary") ||  it.key.equals("children_amount")
        }
        val servantProperties = keysNValues.asSequence().filter { it.key.equals("activity") }

        var questionMarks = employeeProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET ${questionMarks} WHERE " +
                        "employees.id = (SELECT employee_id FROM servants WHERE servants.id = ?)")

        var count = 1

        for (it in employeeProperties) {
            when (it.key) {
                "fio", "sex" -> {
                    stmt.setString(count++, it.value)
                }
                "birth_date",  "hire_date" -> {
                    stmt.setDate(count++, Date.valueOf(it.value))
                }
                "salary",  "children_amount", "origin" -> {
                    stmt.setInt(count++, it.value.toInt())
                }
            }
        }
        stmt.setInt(employeeProperties.toList().size + 1, id)
        stmt.executeUpdate()

        questionMarks = servantProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

        stmt = dataSource.connection.prepareStatement(
                "UPDATE servants SET ${questionMarks} WHERE servants.id = ?")

        count = 1
        for (it in servantProperties) {
            when (it.key) {
                "activity" -> {
                    stmt.setString(count++, it.value)
                }
            }
        }
        stmt.setInt(servantProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }

    fun updateServant(toUpdate: Servant) {
        val employeesDao = EmployeesDao(dataSource)
        employeesDao.updateEmployee(toUpdate.employee)

        val stmt = dataSource.connection.prepareStatement(
                "UPDATE servants SET activity = ? WHERE " +
                        "servants.id = ?"
        )
        stmt.setString(1, toUpdate.activity)
        stmt.setInt(2, toUpdate.id!!)
    }

    fun getServantById(id: Int): Servant? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM servants WHERE id = ?"
        )
        stmt.setInt(1, id)
        val res = stmt.executeQuery()

        return if (res.next()) {
            val employeesDao = EmployeesDao(dataSource)
            val relatedEmployee = employeesDao.getEmployeeById(res.getInt("employee_id"))
            return if (relatedEmployee == null) {
                null
            } else {
                Servant(
                        res.getInt("id"),
                        relatedEmployee,
                        res.getString("activity")
                )
            }
        } else {
            null
        }
    }

    fun getServantByName(fio: String): Servant? {
        val employeesDao = EmployeesDao(dataSource)
        val relatedEmployee = employeesDao.getEmployeeByName(fio)
        return if (relatedEmployee != null) {

            val stmt = dataSource.connection.prepareStatement(
                    "SELECT * FROM servants WHERE servants.employee_id = ?")
            stmt.setInt(1, relatedEmployee.id)
            val res = stmt.executeQuery()

            return if (res.next()) {
                Servant(
                        res.getInt("id"),
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
    
}