package theater.model.data

import theater.model.Performance
import java.sql.Date

data class PerformanceData(
        val id: Int,
        val production_designer: Int,
        val production_director: Int,
        val production_conductor: Int,
        val season: Int,
        val spectacle_id: Int
) {
    constructor(p: Performance): this(p.id, p.production_designer,
        p.production_director, p.production_conductor, p.season, p.spectacle_id)
}