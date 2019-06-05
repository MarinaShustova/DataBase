package theater.model.data

import theater.model.Producer
import java.sql.Date

data class ProducerData(
        val id: Int,
        val fio: String,
        val sex: String,
        val birthDate: String,
        val childrenAmount: Int,
        val salary: Int,
        val origin: String,
        val hireDate: String,
        val activity: String
) {
    constructor(p: Producer): this(p.id, p.employee.fio, p.employee.sex, p.employee.birthDate.toString(),
            p.employee.childrenAmount, p.employee.salary, p.employee.origin, p.employee.hireDate.toString(),
            p.activity)
}