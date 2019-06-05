package theater.model

import java.sql.Date

data class Employee(var id: Int, val fio: String, val sex: String,
                    val birthDate: Date, val childrenAmount: Int, val salary: Int,
                    val origin: String, val hireDate: Date)