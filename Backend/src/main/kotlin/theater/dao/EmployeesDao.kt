package theater.dao

import theater.model.Country
import theater.model.Employee
import java.lang.IllegalArgumentException
import java.sql.ResultSet
import javax.sql.DataSource
import java.sql.Statement
import java.sql.Date
import java.sql.PreparedStatement

class EmployeesDao(private val dataSource: DataSource) {
    fun createEmployee(toCreate: Employee): Int {
        // fio sex birth child salary origin hire
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO employees (fio, sex, birth_date, " +
                        "children_amount, salary, origin, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        val countryDao = CountryDao(dataSource)
        val relatedCountry = countryDao.getCountryByName(toCreate.origin)
        if (relatedCountry == null) throw Exception("No country with this name in database")
        stmt.run {
            setString(1, toCreate.fio)
            setString(2, toCreate.sex)
            setDate(3, toCreate.birthDate)
            setInt(4, toCreate.childrenAmount)
            setInt(5, toCreate.salary)
            setInt(6, relatedCountry.id)
            setDate(7, toCreate.hireDate)

            executeUpdate()
        }
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun updateEmployee(toUpdate: Employee) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE employees SET (fio = ?, sex = ?, birth_date = ?," +
                        "children_amount = ?, salary = ?, origin, hire_date) WHERE " +
                        "employees.id = ?"
        )

        val relatedCountry = CountryDao(dataSource).getCountryByName(toUpdate.origin)
        if (relatedCountry == null) throw IllegalArgumentException("No country with this name in database")

        stmt.run {
            setString(1, toUpdate.fio)
            setString(2, toUpdate.sex)
            setDate(3, toUpdate.birthDate)
            setInt(4, toUpdate.childrenAmount)
            setInt(5, toUpdate.salary)
            setInt(6, relatedCountry.id)
            setDate(7, toUpdate.hireDate)

            executeUpdate()
        }
    }

    private fun buildEmployee(queryResult: ResultSet): Employee? {
        val countryDao = CountryDao(dataSource)
        val employeeCountry = countryDao.getCountry(queryResult.getInt("origin"))
        return if (employeeCountry != null) {
            Employee(queryResult.getInt("id"),
                    queryResult.getString("fio"),
                    queryResult.getString("sex"),
                    queryResult.getDate("birth_date"),
                    queryResult.getInt("children_amount"),
                    queryResult.getInt("salary"),
                    employeeCountry.name,
                    queryResult.getDate("hire_date")
            )
        } else {
            null
        }
    }


    fun getEmployeeById(id: Int): Employee? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE employees.id = ?"
        )
        stmt.setInt(1, id)
        val res = stmt.executeQuery()


        return if (res.next()) {
            buildEmployee(res)
        } else {
            null
        }
    }

    fun getEmployeeByName(fio: String): Employee? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE employees.fio = ?"
        )
        stmt.setString(1, fio)
        val res = stmt.executeQuery()

        return if (res.next()) {
            buildEmployee(res)
        } else {
            null
        }
    }


    fun getEmployeesBySex(sex: String): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE employees.sex = ?"
        )
        stmt.setString(1, sex)
        return getEmployeesBy(stmt)
    }

    fun getEmployeesByExperience(years: Int): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE date_part('year', age(current_date, hire_date)) >= ?"
        )

        stmt.setInt(1, years)
        return getEmployeesBy(stmt)
    }

    fun getEmployeesByBirthDate(birth: Date): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE birth_date = ?"
        )

        stmt.setDate(1, birth)
        return getEmployeesBy(stmt)
    }

    fun getEmployeesByAge(age: Int): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE date_part('year', age(current_date, birth_date)) = ?"
        )

        stmt.setInt(1, age)
        return getEmployeesBy(stmt)
    }

    fun getEmployeesByChildrenAmount(count: Int): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE children_amount >= ?"
        )

        stmt.setInt(1, count)
        return getEmployeesBy(stmt)
    }

    fun getEmployeesBySalary(salary: Int): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees WHERE salary >= ?"
        )

        stmt.setInt(1, salary)
        stmt.executeQuery()
        return getEmployeesBy(stmt)
    }

    fun getEmployees(): List<Employee> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT * FROM employees"
        )
        stmt.executeQuery()
        return getEmployeesBy(stmt)
    }

    private fun getEmployeesBy(stmt: PreparedStatement): List<Employee> {
        val res = stmt.executeQuery()

        val resultList = ArrayList<Employee>()

        while (res.next()) {
            val employee = buildEmployee(res)
            if (employee == null) continue
            resultList.add(employee)
        }

        return resultList
    }
    
 /*   fun getEmployee(id: Int): Employee? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT * FROM employees WHERE employees.id = ?"
        )
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Employee(
                rs.getInt("id"), rs.getString("name"),
                rs.getString("sex"), rs.getDate("birthDate"),
                rs.getInt("childrenAmount"), rs.getInt("salary"),
                rs.getString("origin"), rs.getDate("hireDate"))
        } else {
            null
        }
    }*/
}