package ru.nsu.fit.theater.model

import java.sql.Date

data class Author(var id: Int,
                  val name: String,
                  val surname: String,
                  val birthDate: Date,
                  val deathDate: Date?,
                  val country: Country)