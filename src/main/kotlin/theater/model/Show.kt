package theater.model

import java.sql.Timestamp

data class Show(val id: Int, val date: Timestamp, val premiere: Boolean)