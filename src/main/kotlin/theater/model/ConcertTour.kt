package theater.model

import java.sql.Date

data class ConcertTour(val id: Long?, val city: String, val start_date: Date,
                       val finish_date: Date)