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
                    || it.key.equals("birth_date") ||  it.key.equals("hire_date")
                    || it.key.equals("salary") ||  it.key.equals("children_amount")
        }
        val producerProperties = keysNValues.asSequence().filter { it.key.equals("activity") }

        var questionMarks = employeeProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET ${questionMarks} WHERE " +
                        "employees.id = (SELECT employee_id FROM producers WHERE producers.id = ?)")

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

        questionMarks = producerProperties.asSequence().map{ "${it.key} = ?" }.joinToString(", ")

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

}