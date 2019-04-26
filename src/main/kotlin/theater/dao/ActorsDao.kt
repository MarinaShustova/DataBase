package theater.dao

import theater.model.Actor
import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource

class ActorsDao(private val dataSource: DataSource) {
    fun createActor(toCreate: Actor): Long {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO actors (employee_id, is_student) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, toCreate.employee.id)
        stmt.setBoolean(2, toCreate.isStudent)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun deleteActor(id: Long) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM actors WHERE actors.id = ?")
        stmt.setInt(1, id.toInt())
        stmt.executeUpdate()
    }

    fun updateActor(id: Long, keysNValues: Map<String, String>) { //updating in two statements
        val employeeProperties = keysNValues.asSequence().filter {
            it.key.equals("fio") || it.key.equals("sex") || it.key.equals("origin")
                    || it.key.equals("birth_date") ||  it.key.equals("hire_date")
                    || it.key.equals("salary") ||  it.key.equals("children_amount")
        }
        val actorProperties = keysNValues.asSequence().filter { it.key.equals("is_student") }

        var questionMarks = employeeProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET ${questionMarks} WHERE " +
                        "employees.id = (SELECT employee_id FROM actors WHERE actors.id = ?)")

        var count = 1

        for (it in employeeProperties) {
            when (it.key) {
                "fio", "sex", "origin" -> {
                    stmt.setString(count++, it.value)
                }
                "birth_date",  "hire_date" -> {
                    stmt.setDate(count++, Date.valueOf(it.value))
                }
                "salary",  "children_amount" -> {
                    stmt.setInt(count++, it.value.toInt())
                }
            }
        }
        stmt.setLong(employeeProperties.toList().size + 1, id)
        stmt.executeUpdate()

        questionMarks = actorProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

        stmt = dataSource.connection.prepareStatement(
                "UPDATE actors SET ${questionMarks} WHERE actors.id = ?")

        count = 1
        for (it in actorProperties) {
            when (it.key) {
                "is_student" -> {
                    stmt.setBoolean(count++, it.value.toBoolean())
                }
            }
        }
        stmt.setLong(actorProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }

}