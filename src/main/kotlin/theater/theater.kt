package theater

import theater.controller.*
import theater.controller.commandLineController.AuthorCommandLineController
import theater.dao.*
import theater.service.*

// create author Уильям,Шекспир,1564-04-01,1616-04-23,Англия
// create author Максим,Горький,1868-03-28,1936-06-18,Российская империя
// create author Александр,Островский,1823-04-12,1886-06-14,Российская империя
// create author Жан-Батист,Мольер,1622-01-15,1673-02-17,Франция

// create spectacle Ромео и Джульетта,Трагедия,6
// create spectacle Леди Макбет,Трагедия,12

// create author for spectacle Уильям,Шекспир,Ромео и Джульетта
// create author for spectacle Уильям,Шекспир,Леди Макбет

// create show

fun main() {
    val db = Db()
    val theaterDs = TheaterDataSource(db.dataSource)
    val performanceDao = PerformanceDao(theaterDs)
    val concertTourDao = ConcertTourDao(theaterDs)
    val roleDao = RoleDao(theaterDs)
    val featureDao = FeatureDao(theaterDs)
    val employeesDao = EmployeesDao(db.dataSource)
    val producersDao = ProducersDao(db.dataSource)
    val actorsDao = ActorsDao(db.dataSource)
    val musiciansDao = MusiciansDao(db.dataSource)
    val servantsDao = ServantsDao(db.dataSource)
    val employeeService = EmployeeService(theaterDs, employeesDao, producersDao, actorsDao, musiciansDao, servantsDao)
    val employeeController = EmployeeController(employeeService)
    val countryDao = CountryDao(db.dataSource)
    val authorDao = AuthorDao(db.dataSource)
    val spectacleDao = SpectacleDao(db.dataSource)
    val performanceController =
        PerformanceController(PerformanceService(theaterDs, performanceDao, concertTourDao, roleDao,
            featureDao, employeesDao, countryDao))
    val authorController = AuthorCommandLineController(AuthorService(db.dataSource, authorDao), CountryService(db.dataSource, countryDao))
    val spectacleController = SpectacleController(SpectacleService(db.dataSource, spectacleDao),
            GenreService(db.dataSource, GenreDao(db.dataSource)), AuthorService(db.dataSource, authorDao), CountryService(db.dataSource, countryDao))
//    val authorController =
//        AuthorController(AuthorService(db.dataSource, authorDao), CountryService(db.dataSource, countryDao))
//    val spectacleController = SpectacleController(
//        SpectacleService(db.dataSource, spectacleDao),
//        GenreService(db.dataSource, GenreDao(db.dataSource)),
//        AuthorService(db.dataSource, authorDao),
//        CountryService(db.dataSource, countryDao)
//    )
    val ticketController = TicketController(TicketService(db.dataSource, TicketDao(db.dataSource)))
    val showController = ShowController(ShowService(db.dataSource, ShowDao(db.dataSource)))

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

    val countryController = CountryController(CountryService(db.dataSource, countryDao))
    var name = "Англия"
    println("create country $name")
    var country = countryController.createCountry(name)
    println("created country table row = $country")
    name = "Российская империя"
    println("create country $name")
    country = countryController.createCountry(name)
    println("created country table row = $country")
    name = "Франция"
    println("create country $name")
    country = countryController.createCountry(name)
    println("created country table row = $country")

    generateSequence { print("> "); readLine() }
        .takeWhile { it != "exit" }
        .map {
            try {
                if (it.startsWith("create")) {
                    if (it.contains("performance")) {
//                        performanceController.createPerformance(it.substring("create performance".length).trim())
                    } else if (it.contains("performances")) {
//                        performanceController.createPerformances(it.substring("create performances".length).trim())
                    } else if (it.contains("tour")) {
//                        performanceController.createConcertTour(it.substring("create tour".length).trim())
                    } else if (it.contains("tours")) {
//                        performanceController.createConcertTours(it.substring("create tours".length).trim())
                    } else if (it.contains("role")) {
                        performanceController.createRole(it.substring("create role".length).trim())
                    } else if (it.contains("roles")) {
//                        performanceController.createRoles(it.substring("create roles".length).trim())
                    } else if (it.contains("feature")) {
//                        performanceController.createFeature(it.substring("create feature".length).trim())
                    } else if (it.contains("features")) {
//                        performanceController.createFeatures(it.substring("create features".length).trim())
                    } else if (it.contains("producer")) {
                        employeeController.createProducer(it.substring("create producer".length).trim());
                    } else if (it.contains("actor")) {
                        employeeController.createActor(it.substring("create actor".length).trim());
                    } else if (it.contains("musician")) {
                        employeeController.createMusician(it.substring("create musician".length).trim());
                    } else if (it.contains("servant")) {
                        employeeController.createServant(it.substring("create servant".length).trim());
                    } else if (it.contains("author for spectacle")) {
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
                }
                else if (it.startsWith("get")) {
                    if (it.contains("performances")) {
//                        performanceController.getPerformances(it.substring("get performances".length).trim())
                    } else if (it.contains("tours")) {
//                        performanceController.getConcertTours(it.substring("get tours".length).trim())
                    } else if (it.contains("roles")) {
//                        performanceController.getRoles(it.substring("get roles".length).trim())
                    } else if (it.contains("features")) {
//                        performanceController.getFeatures(it.substring("get features".length).trim())
                    } else if (it.contains("spectacle")) {
                        if (it.contains("of genre")) {
                            spectacleController.getSpectacleOfGenre(it.substring("get spectacle of genre".length).trim())
                        } else if (it.contains("of author life period")) {
                            spectacleController.getSpectacleOfCurAuthorLifePeriod(
                                    it.substring("get spectacle of author life period".length).trim())
                        } else if (it.contains("of author")) {
                            spectacleController.getSpectacleOfAuthor(it.substring("get spectacle of author".length).trim())
                        } else if (it.contains("of country")) {
                            spectacleController.getSpectacleOfCountry(it.substring("get spectacle of country".length).trim())
                        } else {
                            spectacleController.getSpectacle(it.substring("get spectacle".length).trim())
                        }
                    } else if (it.contains("author")) {
                        if (it.contains("of country")) {
                            authorController.getAuthorsOfCountry(it.substring("get author of country".length).trim())
                        } else if (it.contains("of century")) {
                            authorController.getAuthorOfCurCentury(it.substring("get author of century".length).trim())
                        } else {
                            authorController.getAuthor(it.substring("get author".length).trim())
                        }
                    } else if (it.contains("show")) {
                        showController.getShow(it.substring("get spectacle".length).trim())
                    } else if (it.contains("ranks")) {
                        if (it.contains("sex:")) {
                            employeeController.getActorWithRankSex(
                                it.substring("get actors with ranks, sex:".length).trim()
                            )
                        } else if (it.contains("younger")) {
                            employeeController.getActorWithRankAge(
                                it.substring("get actors with ranks, younger then ".length).trim(), -1
                            )
                        } else if (it.contains("older")) {
                            employeeController.getActorWithRankAge(
                                it.substring("get actors with ranks, older then ".length).trim(), 1
                            )
                        } else if (it.contains("contests")) {
                            employeeController.getActorWithRankContests(
                                it.substring("get actors with ranks, given on contests ".length).trim()
                            )
                        } else {
                            employeeController.getActorWithRank()
                        }
                    } else if (it.contains("troupe")) {
//                        employeeController.getTourTroupe(it.substring("get tour troupe".length).trim(),
//                            spectacleDao)
                    } else if (it.contains("info")) {
//                        employeeController.getPerformanceInfo(it.substring("get spectacle info".length).trim(),
//                            spectacleDao, countryDao)
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("update")) {
                    if (it.contains("performance")) {
//                        performanceController.updatePerformance(it.substring("update performance".length).trim())
                    } else if (it.contains("tour")) {
//                        performanceController.updateConcertTour(it.substring("update tour".length).trim())
                    } else if (it.contains("role")) {
//                        performanceController.updateRole(it.substring("update role".length).trim())
                    } else if (it.contains("feature")) {
//                        performanceController.updateFeature(it.substring("update feature".length).trim())
                    } else if (it.contains("producer")) {
                        employeeController.updateProducer(it.substring("update producer".length).trim());
                    } else if (it.contains("actor")) {
                        employeeController.updateActor(it.substring("update actor".length).trim());
                    } else if (it.contains("musician")) {
                        employeeController.updateMusician(it.substring("update musician".length).trim());
                    } else if (it.contains("servant")) {
                        employeeController.updateServant(it.substring("update servant".length).trim());
                    } else if (it.contains("author")) {
                        authorController.updateAuthor(it.substring("update author".length).trim())
                    } else if (it.contains("spectacle")) {
                        spectacleController.updateSpectacle(it.substring("update spectacle".length).trim())
                    } else if (it.contains("show")) {
                        showController.updateShow(it.substring("update show".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("delete")) {
                    if (it.contains("performance")) {
//                        performanceController.deletePerformance(it.substring("delete performance".length).trim())
                    } else if (it.contains("tour")) {
//                        performanceController.deleteConcertTour(it.substring("delete tour".length).trim())
                    } else if (it.contains("role")) {
//                        performanceController.deleteRole(it.substring("delete role".length).trim())
                    } else if (it.contains("feature")) {
//                        performanceController.deleteFeature(it.substring("delete feature".length).trim())
                    } else if (it.contains("producer")) {
                        employeeController.deleteProducer(it.substring("delete producer".length).trim());
                    } else if (it.contains("actor")) {
                        employeeController.deleteActor(it.substring("delete actor".length).trim());
                    } else if (it.contains("musician")) {
                        employeeController.deleteMusician(it.substring("delete musician".length).trim());
                    } else if (it.contains("servant")) {
                        employeeController.deleteServant(it.substring("delete servant".length).trim());
                    } else if (it.contains("author")) {
                        authorController.deleteAuthor(it.substring("delete author".length).trim())
                    } else if (it.contains("spectacle")) {
                        spectacleController.deleteSpectacle(it.substring("delete spectacle".length).trim())
                    } else if (it.contains("show")) {
                        showController.deleteShow(it.substring("delete show".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("link")){
                    if (it.contains("performance") && it.contains("tour")) {
//                        performanceController.addConcertTourToPerformance(it.substring("link performance with tour".length).trim())
                    } else if (it.contains("role") && it.contains("performance")) {
//                        performanceController.addRoleToPerformance(it.substring("link performance with role".length).trim())
                    } else if (it.contains("feature") && it.contains("role")) {
//                        performanceController.addFeatureToRole(it.substring("link role with feature".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else {
                    "Unknown command: $it"
                }
            } catch (e: Exception) {
                println("Error has been occured: $e")
                e.printStackTrace()
            }
        }.forEach { println(it) }
}