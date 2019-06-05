package theater.controller

import org.springframework.web.bind.annotation.*
import theater.exception.GenreNotFoundException
import theater.model.Genre
import theater.service.GenreService

@RestController
@RequestMapping("/genres")
class GenreController(private val genreService: GenreService) {

    @PostMapping("/create")
    fun createGenre(@RequestParam name: String): Genre {
        return Genre(genreService.createGenre(Genre(-1, name)), name)
    }

    @GetMapping("/{id}")
    fun getGenre(@PathVariable id: Int): Genre {
        return genreService.getGenre(id) ?: throw GenreNotFoundException()
    }

    @GetMapping("/get")
    fun getGenres(): ArrayList<Genre> {
        return genreService.getGenres()
    }

    @PostMapping("/update")
    fun updateGenre(@RequestBody genre: Genre) {
        genreService.updateGenre(genre)
    }

    @PostMapping("/delete")
    fun deleteGenre(@RequestParam name: String) {
        genreService.deleteGenre(name)
    }

    @PostMapping("/delete/{id}")
    fun deleteGenre(@PathVariable id: Int) {
        genreService.deleteGenre(id)
    }

}