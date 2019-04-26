package theater

import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource

class MusiciansDao(private val dataSource: DataSource) {
    fun createMusician(toCreate: Musician): Long {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO musicians (employee_id, instrument) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, toCreate.employee.id)
        stmt.setString(2, toCreate.instrument)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun deleteMusician(id: Long) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM musicians WHERE musicians.id = ?")
        stmt.setInt(1, id.toInt())
        stmt.executeUpdate()
    }

    fun updateMusician(id: Long, keysNValues: Map<String, String>) { //updating in two statements
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
                "fio", "sex", "origin" -> {
                    stmt.setString(count++, it.value)
                }
                "birth_date", "hire_date" -> {
                    stmt.setDate(count++, Date.valueOf(it.value))
                }
                "salary", "children_amount" -> {
                    stmt.setInt(count++, it.value.toInt())
                }
            }
        }
        stmt.setLong(employeeProperties.toList().size + 1, id)
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
        stmt.setLong(musicianProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }
}