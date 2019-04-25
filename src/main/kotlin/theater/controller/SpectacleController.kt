package theater.controller

import theater.model.Genre
import theater.model.Spectacle
import theater.service.AuthorService
import theater.service.CountryService
import theater.service.GenreService
import theater.service.SpectacleService
import java.sql.Timestamp

class SpectacleController(private val spectacleService: SpectacleService,
                          private val genreService: GenreService,
                          private val authorService: AuthorService,
                          private val countryService: CountryService) {

    fun createSpectacle(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected"
        }

        val genre = genreService.getGenreByName(args[1]) ?: return "Genre with name ${args[1]} not found"

        val spectacle = Spectacle(-1, args[0], genre, args[2].toInt())
        spectacle.id = spectacleService.createSpectacle(spectacle)
        return "Created spectacle ${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory}"
    }

    fun createGenre(name: String): Genre {
        return Genre(genreService.createGenre(Genre(-1, name)), name)
    }

    fun createAuthorOfSpectacle(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected"
        }

        val author = authorService.getAuthorByFullName(args[0], args[1])
                ?: return "Author with name ${args[0]} ${args[1]}" +
                        " not found"
        val spectacle = spectacleService.getSpectacleByName(args[2]) ?: return "Spectacle with name ${args[2]}" +
                " not found"
        val id = spectacleService.createAuthorOfSpectacle(spectacle, author)
        return "Created dependency between spectacle ${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory}" +
                " and author: ${author.id}, ${author.name}, ${author.surname}, ${author.birthDate}, ${author.deathDate}," +
                " ${author.country.name} with id $id"
    }

    fun getSpectacle(argsStr: String): String {
        val spectacle = spectacleService.getSpectacle(argsStr.toInt())
                ?: return "Can't find author with id ${argsStr.toInt()}"
        return "Created spectacle: ${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory}"
    }

    fun getSpectacleOfGenre(argsStr: String): String {
        val genre = genreService.getGenreByName(argsStr) ?: return "Wrong genre name"
        val spectaclesList = spectacleService.getSpectacleOfGenre(genre)
        var res = ""
        for (spectacle in spectaclesList) {
            res += "${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory} \n"
        }
        return res
    }

    fun getSpectacleOfAuthor(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        val author = authorService.getAuthorByFullName(args[0], args[1]) ?: return "Unknown author"
        val spectaclesList = spectacleService.getSpectacleOfAuthor(author)
        var res = ""
        for (spectacle in spectaclesList) {
            res += "${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory} \n"
        }
        return res
    }

    fun getSpectacleOfCountry(argsStr: String): String {
        val country = countryService.getCountryByName(argsStr) ?: return "Unknown country"
        val spectaclesList = spectacleService.getSpectacleOfCountry(country)
        var res = ""
        for (spectacle in spectaclesList) {
            res += "${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory} \n"
        }
        return res
    }

    fun getSpectacleOfCurAuthorLifePeriod(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        return try {
            val spectaclesList = spectacleService.getSpectacleOfCurAuthorLifePeriod(Timestamp.valueOf(args[0]),
                    Timestamp.valueOf(args[1]))
            var res = ""
            for (spectacle in spectaclesList) {
                res += "${spectacle.id}, \"${spectacle.name}\", ${spectacle.genre.name}, ${spectacle.ageCategory} \n"
            }
            res
        } catch (ex: IllegalArgumentException) {
            "Args should be datetime in format: yyyy-[m]m-[d]d hh:mm:ss"
        }
    }

    fun updateSpectacle(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 4) {
            return "4 arg expected"
        }

        val genre = genreService.getGenreByName(args[2]) ?: return "Genre with name ${args[2]} not found"
        val spectacle = Spectacle(args[0].toInt(), args[1], genre, args[3].toInt())
        spectacleService.updateSpectacle(spectacle)
        return "spectacle updated"
    }

    fun deleteSpectacle(argsStr: String): String {
        spectacleService.deleteSpectacle(argsStr.toInt())
        return "Deleted spectacle $argsStr"
    }

}