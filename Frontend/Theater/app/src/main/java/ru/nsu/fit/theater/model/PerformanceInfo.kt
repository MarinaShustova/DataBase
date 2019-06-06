package ru.nsu.fit.theater.model

import java.sql.Date

data class PerformanceInfo (val producers : List<Producer>,
                            val actors: List<Actor>,
                            val author: Author,
                            val premiere: Date)