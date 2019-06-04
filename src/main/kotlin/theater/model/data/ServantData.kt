package theater.model.data

import theater.model.Servant

data class ServantData(
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
    constructor(s: Servant): this(s.id, s.employee.fio, s.employee.sex, s.employee.birthDate.toString(),
            s.employee.childrenAmount, s.employee.salary, s.employee.origin, s.employee.hireDate.toString(),
            s.activity)
}