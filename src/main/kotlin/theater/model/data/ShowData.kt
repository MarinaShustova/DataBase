package theater.model.data

import java.sql.Timestamp

data class ShowData(val date: Timestamp,
                    val premiere: Boolean,
                    val performanceId: Int)