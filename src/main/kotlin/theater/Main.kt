package theater

import theater.controller.*
import theater.dao.*
import theater.model.Country
import theater.service.*
import java.sql.Date

fun main(args: Array<String>) {
    // create author Уильям,Шекспир,1564-04-01,1616-04-23,Англия
    // create author Максим,Горький,1868-03-28,1936-06-18,Российская империя
    // create author Александр,Островский,1823-04-12,1886-06-14,Российская империя
    // create author Жан-Батист,Мольер,1622-01-15,1673-02-17,Франция

    // create spectacle Ромео и Джульетта,трагедия,6
    // create spectacle Леди Макбет,трагедия,12

    // create author for spectacle Уильям,Шекспир,Ромео и Джульетта
    // create author for spectacle Уильям,Шекспир,Леди Макбет

    // create show

    val db = Db()
    val univDs = db.dataSource//UnivDataSource(db.dataSource)
    val countryDao = CountryDao(univDs)
    val authorDao = AuthorDao(univDs)
    val spectacleDao = SpectacleDao(univDs)
    val authorController = AuthorController(AuthorService(univDs, authorDao), CountryService(univDs, countryDao))
    val spectacleController = SpectacleController(SpectacleService(univDs, spectacleDao),
            GenreService(univDs, GenreDao(univDs)), AuthorService(univDs, authorDao), CountryService(univDs, countryDao))
    val ticketController = TicketController(TicketService(univDs, TicketDao(univDs)))
    val showController = ShowController(ShowService(univDs, ShowDao(univDs)))

//    var name = "Комедия"
//    println("create genre $name")
//    var genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")
//    name = "Трагедия"
//    println("create genre $name")
//    genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")
//    name = "Драма"
//    println("create genre $name")
//    genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")
//    name = "Мюзикл"
//    println("create genre $name")
//    genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")
//    name = "Водевиль"
//    println("create genre $name")
//    genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")
//    name = "Мелодрама"
//    println("create genre $name")
//    genre = spectacleController.createGenre(name)
//    println("created genre table row = $genre")

    generateSequence { print("> "); readLine() }
            .takeWhile { it != "exit" }
            .map {
                try {
                    if (it.startsWith("create")) {
                        if (it.contains("author for spectacle")) {
                            spectacleController.createAuthorOfSpectacle(it.substring("create author for spectacle".length).trim())
                        } else if (it.contains("spectacle")) {
                            spectacleController.createSpectacle(it.substring("create spectacle".length).trim())
                        } else if (it.contains("author")) {
                            authorController.createAuthor(it.substring("create author".length).trim())
                        } else if (it.contains("ticket for show")) {
                            ticketController.addTicketForShow(it.substring("create ticket for show".length).trim())
                        } else if (it.contains("ticket")) {
                            ticketController.createTicket(it.substring("create ticket".length).trim())
                        } else if (it.contains("show")) {
                            showController.createShow(it.substring("create show".length).trim())
                        } else {
                            "Unknown command"
                        }
                    } else if (it.startsWith("update")) {
                        if (it.contains("author")) {
                            authorController.updateAuthor(it.substring("update author".length).trim())
                        } else if (it.contains("spectacle")) {
                            spectacleController.updateSpectacle(it.substring("update spectacle".length).trim())
                        } else if (it.contains("show")) {
                            showController.updateShow(it.substring("update show".length).trim())
                        } else {
                            "Unknown command"
                        }
                    } else if (it.startsWith("get")) {
                        if (it.contains("author")) {
                            if (it.contains("of country")) {
                                authorController.getAuthorsOfCountry(it.substring("get author of country".length).trim())
                            } else if (it.contains("of century")) {
                                authorController.getAuthorOfCurCentury(it.substring("get author of century".length).trim())
                            } else {
                                authorController.getAuthor(it.substring("get author".length).trim())
                            }
                        } else if (it.contains("spectacle")) {
                            if (it.contains("of genre")) {
                                spectacleController.getSpectacleOfGenre(it.substring("get spectacle of genre".length).trim())
                            } else if (it.contains("of author")) {
                                spectacleController.getSpectacleOfAuthor(it.substring("get spectacle of author".length).trim())
                            } else if (it.contains("of country")) {
                                spectacleController.getSpectacleOfCountry(it.substring("get spectacle of country".length).trim())
                            } else if (it.contains("of author life period")) {
                                spectacleController.getSpectacleOfCurAuthorLifePeriod(
                                        it.substring("get spectacle author life period".length).trim())
                            } else {
                                spectacleController.getSpectacle(it.substring("get spectacle".length).trim())
                            }
                        } else if (it.contains("show")) {
                            showController.getShow(it.substring("get spectacle".length).trim())
                        } else {
                            "Unknown command"
                        }
                    } else if (it.startsWith("delete")) {
                        if (it.contains("author")) {
                            authorController.deleteAuthor(it.substring("delete author".length).trim())
                        } else if (it.contains("spectacle")) {
                            spectacleController.deleteSpectacle(it.substring("delete spectacle".length).trim())
                        } else if (it.contains("show")) {
                            showController.deleteShow(it.substring("delete show".length).trim())
                        } else {
                            "Unknown command"
                        }
                    } else {
                        "Unknown command: $it"
                    }
                } catch (e: Exception) {
                    println("Error has been occured: $e")
                    e.printStackTrace()
                }
            }.forEach { println(it) }
}