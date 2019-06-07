package ru.nsu.fit.theater.retrofit.model

import java.sql.Date

data class IdAuthorData(val id: Int,
                        val name: String,
                        val surname: String,
                        val birthDate: Date,
                        val deathDate: Date?,
                        val countryName: String) {

//    constructor(author: Author) : this(author.name,
//            author.surname, author.birthDate, author.deathDate, author.country.name)

}