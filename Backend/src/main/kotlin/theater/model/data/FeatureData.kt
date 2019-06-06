package theater.model.data

import theater.model.Feature
import java.sql.Date

data class FeatureData(
        val id: Int,
        val name: String,
        val value: String
) {
    constructor(f: Feature): this(f.id, f.name, f.value)
}