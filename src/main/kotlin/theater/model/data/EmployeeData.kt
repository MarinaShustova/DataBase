package theater.model.data

import theater.model.Employee
import java.sql.Date

data class EmployeeData(
                        val id: Int,
                        val fio: String,
                        val sex: String,
                        val birthDate: String,
                        val childrenAmount: Int,
                        val salary: Int,
                        val origin: String,
                        val hireDate: String
) {
    constructor(e: Employee): this(e.id, e.fio, e.sex, e.birthDate.toString(),
            e.childrenAmount, e.salary, e.origin, e.hireDate.toString())
}