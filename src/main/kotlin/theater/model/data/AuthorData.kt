package theater.model.data

import java.sql.Date

data class AuthorData(val name: String,
                      val surname: String,
                      val birthDate: Date,
                      val deathDate: Date?,
                      val countryName: String)