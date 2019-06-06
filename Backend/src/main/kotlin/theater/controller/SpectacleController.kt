package theater.controller

import org.springframework.web.bind.annotation.*
import theater.exception.GenreNotFoundException
import theater.exception.SpectacleNotFoundException
import theater.exception.WrongDataFormatException
import theater.model.Genre
import theater.model.Spectacle
import theater.model.data.AuthorData
import theater.model.data.SpectacleData
import theater.service.GenreService
import theater.service.SpectacleService
import java.sql.Date
import java.sql.Timestamp

@RestController
@RequestMapping("/spectacles")
class SpectacleController(private val spectacleService: SpectacleService) {

    @PostMapping("/create")
    fun createSpectacle(@RequestBody spectacleData: SpectacleData): String {
        val id = spectacleService.createSpectacle(spectacleData)
        return "Created spectacle: $id, \"${spectacleData.name}\"," +
                " ${spectacleData.genreName}, ${spectacleData.ageCategory}"
    }

    @PostMapping("/create/author")
    fun createAuthorOfSpectacle(@RequestParam authorId: Int, @RequestParam spectacleName: String) {
        spectacleService.createAuthorOfSpectacle(authorId, spectacleName)
    }

    @GetMapping("/{id}")
    fun getSpectacle(@PathVariable id: Int): SpectacleData {
        val data = SpectacleData(spectacleService.getSpectacle(id) ?: throw SpectacleNotFoundException())
        return data
    }

    @GetMapping("/get/all")
    fun getSpectacles(): ArrayList<Spectacle> {
        return spectacleService.getSpectacles()
    }

    @GetMapping("/get/genre")
    fun getSpectacleOfGenre(@RequestParam name: String): ArrayList<Spectacle> {
        return spectacleService.getSpectacleOfGenre(name)
    }

    @GetMapping("/get/author")
    fun getSpectaclesOfAuthor(@RequestParam id: Int): ArrayList<Spectacle> {
        return spectacleService.getSpectaclesOfAuthor(id)
    }

    @GetMapping("/get/country")
    fun getSpectacleOfCountry(@RequestParam name: String): ArrayList<Spectacle> {
        return spectacleService.getSpectacleOfCountry(name)
    }

    @GetMapping("/get")
    fun getSpectacleOfAuthorLifeCentury(@RequestParam century: Int): ArrayList<Spectacle> {
        return try {
            val start = Timestamp.valueOf((century - 1).toString() + "01-01-01 00:00:00")
            val end = Timestamp.valueOf(century.toString() + "00-01-01 00:00:00")
            spectacleService.getSpectacleOfCurAuthorLifePeriod(start, end)
        } catch (ex: IllegalArgumentException) {
            throw WrongDataFormatException("Argument should be datetime in format: yyyy-[m]m-[d]d hh:mm:ss")
        }
    }

    @GetMapping("/get/")
    fun getSpectacleOfAuthorLifePeriod(@RequestParam start: Timestamp, @RequestParam end: Timestamp): ArrayList<Spectacle> {
        return try {
            spectacleService.getSpectacleOfCurAuthorLifePeriod(Timestamp.valueOf(start.toString()),
                    Timestamp.valueOf(end.toString()))
        } catch (ex: IllegalArgumentException) {
            throw WrongDataFormatException("Args should be datetime in format: yyyy-[m]m-[d]d hh:mm:ss")
        }
    }

    @PostMapping("/update")
    fun updateSpectacle(@RequestParam id: Int, @RequestBody spectacleData: SpectacleData) {
        spectacleService.updateSpectacle(id, spectacleData)
    }

    @PostMapping("/delete")
    fun deleteSpectacle(@RequestBody spectacleData: SpectacleData): String {
        spectacleService.deleteSpectacle(spectacleData)
        return "Deleted spectacle $spectacleData"
    }

    @PostMapping("/delete/{id}")
    fun deleteSpectacle(@PathVariable id: Int): String {
        spectacleService.deleteSpectacle(id)
        return "Deleted spectacle"
    }
}