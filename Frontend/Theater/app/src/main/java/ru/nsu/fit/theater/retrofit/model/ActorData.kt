package ru.nsu.fit.theater.retrofit.model

import java.sql.Date

data class ActorData(
        val id: Int,
        val fio: String,
        val sex: String,
        val birthDate: String,
        val childrenAmount: Int,
        val salary: Int,
        val origin: String,
        val hireDate: String,
        val isStudent: Boolean
) {
//    constructor(a: Actor): this(a.id, a.employee.fio, a.employee.sex, a.employee.birthDate.toString(),
//            a.employee.childrenAmount, a.employee.salary, a.employee.origin, a.employee.hireDate.toString(),
//            a.isStudent)
}