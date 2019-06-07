package ru.nsu.fit.theater.model

data class Spectacle(var id: Int,
                     val name: String,
                     val genre: Genre,
                     val ageCategory: Int,
                     var author: Int? = null)