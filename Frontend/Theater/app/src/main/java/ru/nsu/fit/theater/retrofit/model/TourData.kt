package ru.nsu.fit.theater.retrofit.model

import java.sql.Date

data class TourData(
    val id: Int,
    val city: String,
    val start_date: Date,
    val finish_date: Date,
    val performance_id: Int
) {
//    constructor(t: ConcertTour): this(t.id, t.city, t.start_date, t.finish_date, t.performance_id)
}