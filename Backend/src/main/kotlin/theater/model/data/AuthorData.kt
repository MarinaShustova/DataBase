package theater.model.data

import theater.model.Author
import java.sql.Date

data class AuthorData(val name: String,
                      val surname: String,
                      val birthDate: Date,
                      val deathDate: Date?,
                      val countryName: String) {

    constructor(author: Author) : this(author.name,
            author.surname, author.birthDate, author.deathDate, author.country.name)

}