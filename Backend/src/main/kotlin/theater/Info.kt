package theater

import theater.model.Actor
import theater.model.Author
import theater.model.Producer
import java.sql.Date

data class Info (val producers : List<Producer>,
                 val actors: List<Actor>,
                 val author: Author,
                 val premiere: Date)