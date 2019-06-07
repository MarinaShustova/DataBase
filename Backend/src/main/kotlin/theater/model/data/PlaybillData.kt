package theater.model.data

import java.sql.Date

class PlaybillData(
        val date: Date,
        val premiere: Boolean,
        val name: String,
        val age: Int,
        val showId: Int
)