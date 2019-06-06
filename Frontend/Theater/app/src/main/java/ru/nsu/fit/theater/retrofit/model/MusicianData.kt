package ru.nsu.fit.theater.retrofit.model

import java.sql.Date

data class MusicianData(
        val id: Int,
        val fio: String,
        val sex: String,
        val birthDate: String,
        val childrenAmount: Int,
        val salary: Int,
        val origin: String,
        val hireDate: String,
        val instrument: String
) {
//    constructor(m: Musician): this(m.id, m.employee.fio, m.employee.sex, m.employee.birthDate.toString(),
//            m.employee.childrenAmount, m.employee.salary, m.employee.origin, m.employee.hireDate.toString(),
//            m.instrument)
}