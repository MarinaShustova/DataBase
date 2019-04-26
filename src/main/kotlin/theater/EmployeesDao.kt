package theater

import javax.sql.DataSource
import java.sql.Statement

class EmployeesDao(private val dataSource: DataSource) {
    fun createEmployee(toCreate: Employee): Long {
        // fio sex birth child salary origin hire
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO employees (fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.fio)
        stmt.setString(2, toCreate.sex)
        stmt.setDate(3, toCreate.birthDate)
        stmt.setInt(4, toCreate.childrenAmount)
        stmt.setInt(5, toCreate.salary)
        stmt.setString(6, toCreate.origin)
        stmt.setDate(7, toCreate.hireDate)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

}