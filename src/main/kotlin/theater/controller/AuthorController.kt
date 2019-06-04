package theater.controller

import org.springframework.web.bind.annotation.*
import theater.model.Author
import theater.model.data.AuthorData
import theater.service.AuthorService
import theater.service.CountryService
import java.sql.Date

@RestController
@RequestMapping("/authors")
class AuthorController(private val authorService: AuthorService,
                       private val countryService: CountryService) {

    @PostMapping("/create")
    fun createAuthor(@RequestBody authorData: AuthorData): String {
        val country = countryService.getCountryByName(authorData.countryName)
                ?: return "Country with name ${authorData.countryName} not found"
        val author = Author(-1, authorData.name, authorData.surname, authorData.birthDate, authorData.deathDate, country)
        author.id = authorService.createAuthor(author)
        return "Created author: ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
                " ${author.country.name}"
    }

//    fun createAuthors(argsStr: String): String {
//
//    }

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Int): String {
        val author = authorService.getAuthor(id) ?: return "Author with id $id not found"
        return "Author: ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, " +
                "${author.deathDate}, ${author.country.name}"
    }

    @GetMapping("/country")
    fun getAuthorsOfCountry(@RequestParam name: String): List<AuthorData> {
        val country = countryService.getCountryByName(name)
                ?: throw IllegalArgumentException("Country with name $name not found")
        val authorsList = authorService.getAuthorsOfCountry(country)
        val res = ArrayList<AuthorData>()
        for (author in authorsList) {
            res.add(AuthorData(author.name, author.surname, author.birthDate, author.deathDate, author.country.name))
        }
        return res
    }

    @GetMapping
    fun getAuthorsOfCurCentury(@RequestParam century: Int): List<AuthorData> {
        return try {
            val startDate = Date.valueOf((century - 1).toString() + "00-01-01")
            val endDate = Date.valueOf((century - 1).toString() + "99-01-01")
            val authorsList = authorService.getAuthorsOfCurCentury(startDate, endDate)
            val res = ArrayList<AuthorData>()
            for (author in authorsList) {
                res.add(AuthorData(author.name, author.surname, author.birthDate, author.deathDate, author.country.name))
            }
            res
        } catch (ex: IllegalArgumentException) {
            throw IllegalArgumentException("arg should be date in format yyyy-[m]m-[d]d")
        }
    }

    fun updateAuthor(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 6) {
            return "6 arg expected: $args"
        }

        val country = countryService.getCountryByName(args[5]) ?: return "Country with name ${args[5]} not found"
        val author = Author(args[0].toInt(), args[1], args[2], Date.valueOf(args[3]), Date.valueOf(args[4]), country)

        authorService.updateAuthor(author)

        return "author updated"
    }

    fun deleteAuthor(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected: $args"
        }

        val author = authorService.getAuthorByFullName(args[0], args[1])
                ?: return "Can't find author ${args[0]} ${args[1]}"
        authorService.deleteAuthor(author)
        return "Deleted author : ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
                " ${author.country.name}"
    }
}