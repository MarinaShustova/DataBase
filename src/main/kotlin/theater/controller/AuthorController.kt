package theater.controller

import org.springframework.web.bind.annotation.*
import theater.exception.AuthorNotFoundException
import theater.model.Author
import theater.model.data.AuthorData
import theater.service.AuthorService
import java.sql.Date

@RestController
@RequestMapping("/authors")
class AuthorController(private val authorService: AuthorService) {

    @PostMapping("/create")
    fun createAuthor(@RequestBody authorData: AuthorData): String {
        val id = authorService.createAuthor(authorData)
        return "Created author: $id, ${authorData.name}, ${authorData.surname}, ${authorData.birthDate}, ${authorData.deathDate}," +
                " ${authorData.countryName}"
    }

//    fun createAuthors(argsStr: String): String {
//
//    }

    @GetMapping("/{id}")
    fun getAuthor(@PathVariable id: Int): AuthorData {
        val author = authorService.getAuthor(id) ?: throw AuthorNotFoundException()
        return AuthorData(author)
    }

    @GetMapping("/get")
    fun getAuthors(): ArrayList<AuthorData> {
        return authorService.getAuthors()
    }

    @GetMapping("/country")
    fun getAuthorsOfCountry(@RequestParam name: String): List<AuthorData> {
        val authorsList = authorService.getAuthorsOfCountry(name)
        val res = ArrayList<AuthorData>()
        for (author in authorsList) {
            res.add(AuthorData(author.name, author.surname, author.birthDate, author.deathDate, author.country.name))
        }
        return res
    }

    @GetMapping
    fun getAuthorsOfCurCentury(@RequestParam century: Int): List<AuthorData> {
        return try {
            val startDate = Date.valueOf((century - 1).toString() + "01-01-01")
            val endDate = Date.valueOf((century).toString() + "00-01-01")
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

    @PostMapping("/update/{id}")
    fun updateAuthor(@PathVariable id: Int, @RequestBody authorData: AuthorData): String {
        authorService.updateAuthor(id, authorData)
        return "author updated"
    }

    @PostMapping("/update")
    fun updateAuthor(@RequestBody author: Author): String {
        authorService.updateAuthor(author)
        return "author ${author.id} updated"
    }

    @PostMapping("/delete")
    fun deleteAuthor(@RequestBody authorData: AuthorData): String {
        val author = authorService.deleteAuthor(authorData)
        return "Deleted author : ${author.id}, ${author.name}, ${author.surname}, " +
                "${author.birthDate}, ${author.deathDate}, ${author.country.name}"
    }

    @GetMapping("/delete/{id}")
    fun deleteAuthor(@PathVariable id: Int) {
        authorService.deleteAuthor(id)
    }
}