package theater.model


data class Performance(
    val id: Int, val production_designer: Int, val production_director: Int,
    val production_conductor: Int, val season: Int
)