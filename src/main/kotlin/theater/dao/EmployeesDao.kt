package theater.dao

import theater.model.Employee
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

    fun getEmployee(id: Long): Employee? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT * FROM employees WHERE employees.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Employee(
                rs.getLong("fid"), rs.getString("name"),
                rs.getString("sex"), rs.getDate("birthDate"),
                rs.getInt("childrenAmount"), rs.getInt("salary"),
                rs.getString("origin"), rs.getDate("hireDate"))
        } else {
            null
        }
    }
}