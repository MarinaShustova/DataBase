package ru.nsu.fit.theater.retrofit.model

import java.sql.Timestamp

data class IdShowData(
        val id: Int,
        val date: Timestamp,
        val premiere: Boolean,
        val performanceId: Int
)