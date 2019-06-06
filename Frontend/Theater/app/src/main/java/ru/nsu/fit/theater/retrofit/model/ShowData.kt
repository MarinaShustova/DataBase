package ru.nsu.fit.theater.retrofit.model

import java.sql.Timestamp

data class ShowData(val date: Timestamp,
                    val premiere: Boolean,
                    val performanceId: Int)