package ru.nsu.fit.theater.retrofit.model

import java.sql.Date

data class PerformanceData(
        val id: Int,
        val production_designer: Int,
        val production_director: Int,
        val production_conductor: Int,
        val season: Int
) {
//    constructor(p: Performance): this(p.id, p.production_designer,
//        p.production_director, p.production_conductor, p.season)
}