package theater.dao

import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource
import theater.model.*
import java.sql.PreparedStatement
import java.sql.ResultSet

class MusiciansDao(private val dataSource: DataSource) {
    fun createMusician(toCreate: Musician): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO musicians (employee_id, instrument) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, toCreate.employee.id)
        stmt.setString(2, toCreate.instrument)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun deleteMusician(id: Int) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM musicians WHERE musicians.id = ?")
        stmt.setInt(1, id)
        stmt.executeUpdate()
    }

    fun updateMusician(id: Int, keysNValues: Map<String, String>) { //updating in two statements
        val employeeProperties = keysNValues.asSequence().filter {
            it.key.equals("fio") || it.key.equals("sex") || it.key.equals("origin")
                    || it.key.equals("birth_date") || it.key.equals("hire_date")
                    || it.key.equals("salary") || it.key.equals("children_amount")
        }
        val musicianProperties = keysNValues.asSequence().filter { it.key.equals("instrument") }

        var questionMarks = employeeProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET ${questionMarks} WHERE " +
                        "employees.id = (SELECT employee_id FROM musicians WHERE musicians.id = ?)")

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
        stmt.setInt(employeeProperties.toList().size + 1, id)
        stmt.executeUpdate()

        questionMarks = musicianProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        stmt = dataSource.connection.prepareStatement(
                "UPDATE musicians SET ${questionMarks} WHERE musicians.id = ?")

        count = 1
        for (it in musicianProperties) {
            when (it.key) {
                "instrument" -> {
                    stmt.setString(count++, it.value)
                }
            }
        }
        stmt.setInt(musicianProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }

    fun updateMusician(toUpdate: Musician) {
        val employeesDao = EmployeesDao(dataSource)
        employeesDao.updateEmployee(toUpdate.employee)

        val stmt = dataSource.connection.prepareStatement(
                "UPDATE musicians SET instrument = ? WHERE " +
                        "musicians.id = ?"
        )
        stmt.setString(1, toUpdate.instrument)
        stmt.setInt(2, toUpdate.id)
    }

    fun getMusicianById(id: Int): Musician? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM musicians WHERE id = ?"
        )
        stmt.setInt(1, id)
        val res = stmt.executeQuery()

        return if (res.next()) {
            val employeesDao = EmployeesDao(dataSource)
            val relatedEmployee = employeesDao.getEmployeeById(res.getInt("employee_id"))
            return if (relatedEmployee == null) {
                null
            } else {
                Musician(
                        res.getInt("id"),
                        relatedEmployee,
                        res.getString("instrument")
                )
            }
        } else {
            null
        }
    }

    fun getMusicianByName(fio: String): Musician? {
        val employeesDao = EmployeesDao(dataSource)
        val relatedEmployee = employeesDao.getEmployeeByName(fio)
        return if (relatedEmployee != null) {

            val stmt = dataSource.connection.prepareStatement(
                    "SELECT * FROM musicians WHERE musicians.employee_id = ?")
            stmt.setInt(1, relatedEmployee.id)
            val res = stmt.executeQuery()

            return if (res.next()) {
                Musician(
                        res.getInt("id"),
                        relatedEmployee,
                        res.getString("instrument")
                )
            } else {
                null
            }
        } else {
            null
        }
    }

    fun buildMusician(res: ResultSet): Musician? {
        val countryDao = CountryDao(dataSource)
        val employeeCountry = countryDao.getCountry(res.getInt("origin"))
        return if (employeeCountry != null) {
            val relatedEmployee = Employee(res.getInt("employee_id"),
                    res.getString("fio"),
                    res.getString("sex"),
                    res.getDate("birth_date"),
                    res.getInt("children_amount"),
                    res.getInt("salary"),
                    employeeCountry.name,
                    res.getDate("hire_date")
            )
            Musician(
                    res.getInt("musician_id"),
                    relatedEmployee,
                    res.getString("instrument"))
        } else {
            null
        }
    }

    fun getMusiciansBySex(sex: String): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees WHERE employees.sex = ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )


        stmt.setString(1, sex)
        return getMusiciansBy(stmt)
    }

    fun getMusiciansByExperience(years: Int): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees " +
                        "WHERE date_part('year', age(current_date, hire_date)) >= ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )

        stmt.setInt(1, years)
        return getMusiciansBy(stmt)
    }

    fun getMusiciansByBirthDate(birth: Date): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees " +
                        "WHERE birth_date = ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )

        stmt.setDate(1, birth)
        return getMusiciansBy(stmt)
    }

    fun getMusiciansByAge(age: Int): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees " +
                        "WHERE date_part('year', age(current_date, birth_date)) = ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )


        stmt.setInt(1, age)
        return getMusiciansBy(stmt)
    }

    fun getMusiciansByChildrenAmount(count: Int): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees " +
                        "WHERE children_count = ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )

        stmt.setInt(1, count)
        return getMusiciansBy(stmt)
    }

    fun getMusiciansBySalary(salary: Int): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM (SELECT * FROM employees " +
                        "WHERE salary >= ?) e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )
        stmt.setInt(1, salary)
        return getMusiciansBy(stmt)
    }

    fun getMusicians(): List<Musician> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT musicians.id as musician_id, employee_id, fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date, instrument " +
                        "FROM employees e " +
                        "JOIN musicians ON musicians.employee_id = e.id)"
        )
        stmt.executeQuery()
        return getMusiciansBy(stmt)
    }
    
    private fun getMusiciansBy(stmt: PreparedStatement): List<Musician> {
        val res = stmt.executeQuery()

        var resultList = ArrayList<Musician>()

        while (res.next()) {
            val musician = buildMusician(res)
            if (musician == null) continue
            resultList.add(musician)
        }

        return resultList
    }

}