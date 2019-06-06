package ru.nsu.fit.theater.retrofit.model

data class SpectacleData(val name: String,
                         val genreName: String,
                         val ageCategory: Int,
                         val authorId: Int?) {
//    constructor(spectacle: Spectacle) : this(spectacle.name,
//            spectacle.genre.name, spectacle.ageCategory, spectacle.author) {
//        println("Spectacle:$spectacle")
//    }
}