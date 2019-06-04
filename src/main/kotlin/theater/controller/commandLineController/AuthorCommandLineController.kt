package theater.controller.commandLineController

import theater.model.Author
import theater.service.AuthorService
import theater.service.CountryService
import java.sql.Date

class AuthorCommandLineController(private val authorService: AuthorService,
                                  private val countryService: CountryService) {

//    fun createAuthor(argsStr: String): String {
//        val args = argsStr.split(",")
//                .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 5) {
//            return "5 arg expected: $args"
//        }
//        val country = countryService.getCountryByName(args[4]) ?: return "Country with name ${args[4]} not found"
//
//        val author = Author(-1, args[0], args[1], Date.valueOf(args[2]), Date.valueOf(args[3]), country)
//        author.id = authorService.createAuthor(author)
//        return "Created author: ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
//                " ${author.country.name}"
//    }
//
//    fun getAuthor(argsStr: String): String {
//        val id = argsStr.toInt()
//        val author = authorService.getAuthor(id) ?: return "Author with id $id not found"
//        return "Created spectacle: ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
//                " ${author.country.name}"
//    }
//
//    fun getAuthorsOfCountry(argsStr: String): String {
//        val country = countryService.getCountryByName(argsStr) ?: return "Country with name $argsStr not found"
//        val authorsList = authorService.getAuthorsOfCountry(country)
//        var res = ""
//        for (author in authorsList) {
//            res += "${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
//                    " ${author.country.name}\n"
//        }
//        return res
//    }
//
//    fun getAuthorOfCurCentury(argsStr: String): String {
//        return try {
//            val startDate = Date.valueOf((argsStr.toInt() - 1).toString() + "00-01-01")
//            val endDate = Date.valueOf((argsStr.toInt() - 1).toString() + "99-01-01")
//            val authorsList = authorService.getAuthorsOfCurCentury(startDate, endDate)
//            var res = ""
//            for (author in authorsList) {
//                res += "${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
//                        " ${author.country.name}\n"
//            }
//            res
//        } catch (ex: IllegalArgumentException) {
//            "arg should be date in format yyyy-[m]m-[d]d"
//        }
//    }
//
//    fun updateAuthor(argsStr: String): String {
//        val args = argsStr.split(",")
//                .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 6) {
//            return "6 arg expected: $args"
//        }
//
//        val country = countryService.getCountryByName(args[5]) ?: return "Country with name ${args[5]} not found"
//        val author = Author(args[0].toInt(), args[1], args[2], Date.valueOf(args[3]), Date.valueOf(args[4]), country)
//
//        authorService.updateAuthor(author)
//
//        return "author updated"
//    }
//
//    fun deleteAuthor(argsStr: String): String {
//        val args = argsStr.split(",")
//                .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 2) {
//            return "2 arg expected: $args"
//        }
//
//        val author = authorService.getAuthorByFullName(args[0], args[1])
//                ?: return "Can't find author ${args[0]} ${args[1]}"
//        authorService.deleteAuthor(author)
//        return "Deleted author : ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
//                " ${author.country.name}"
//    }

}