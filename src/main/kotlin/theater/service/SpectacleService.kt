package theater.service

import theater.dao.AuthorDao
import theater.dao.CountryDao
import theater.dao.GenreDao
import theater.dao.SpectacleDao
import theater.exception.AuthorNotFoundException
import theater.exception.CountryNotFoundException
import theater.exception.GenreNotFoundException
import theater.exception.SpectacleNotFoundException
import theater.model.Author
import theater.model.Country
import theater.model.Genre
import theater.model.Spectacle
import theater.model.data.AuthorData
import theater.model.data.SpectacleData
import java.sql.Timestamp
import javax.sql.DataSource

class SpectacleService(private val dataSource: DataSource,
                       private val spectacleDao: SpectacleDao,
                       private val genreDao: GenreDao,
                       private val authorDao: AuthorDao,
                       private val countryDao: CountryDao) : Service() {

    fun createSpectacle(spectacle: Spectacle): Int {
        return transaction(dataSource) {
            spectacleDao.createSpectacle(spectacle)
        }
    }

    fun createSpectacle(spectacleData: SpectacleData): Int {
        return transaction(dataSource) {
            val genre = genreDao.getGenreByName(spectacleData.genreName) ?: throw GenreNotFoundException()
            var author: Author? = null
            if (spectacleData.authorId != null) {
                author = authorDao.getAuthor(spectacleData.authorId) ?: throw AuthorNotFoundException()
            }
            spectacleDao.createSpectacle(Spectacle(-1, spectacleData.name, genre,
                    spectacleData.ageCategory, author?.id))
        }
    }

    fun createAuthorOfSpectacle(spectacle: Spectacle, author: Author): Int {
        return transaction(dataSource) {
            spectacleDao.createAuthorOfSpectacle(spectacle, author)
        }
    }

    fun createAuthorOfSpectacle(authorId: Int, spectacleName: String): Int {
        return transaction(dataSource) {
            val spectacle = getSpectacleByName(spectacleName) ?: throw SpectacleNotFoundException()
            val author = authorDao.getAuthor(authorId)
                    ?: throw AuthorNotFoundException()
            println(spectacle)
            println(author)
            spectacleDao.createAuthorOfSpectacle(spectacle, author)
        }
    }

    fun getSpectacle(id: Int): Spectacle? {
        return transaction(dataSource) {
            spectacleDao.getSpectacle(id)
        }
    }

    fun getSpectacles(): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectacles()
        }
    }

    fun getSpectacleOfGenre(genre: Genre): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectaclesOfGenre(genre)
        }
    }

    fun getSpectacleOfGenre(genreName: String): ArrayList<Spectacle> {
        return transaction(dataSource) {
            val genre = genreDao.getGenreByName(genreName) ?: throw GenreNotFoundException()
            spectacleDao.getSpectaclesOfGenre(genre)
        }
    }

    fun getSpectaclesOfAuthor(author: Author): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectaclesOfAuthor(author)
        }
    }

    fun getSpectaclesOfAuthor(authorId: Int): ArrayList<Spectacle> {
        return transaction(dataSource) {
            val author = authorDao.getAuthor(authorId) ?: throw AuthorNotFoundException()
            spectacleDao.getSpectaclesOfAuthor(author)
        }
    }

    fun getSpectacleOfCountry(country: Country): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectaclesOfCountry(country)
        }
    }

    fun getSpectacleOfCountry(countryName: String): ArrayList<Spectacle> {
        return transaction(dataSource) {
            val country = countryDao.getCountryByName(countryName) ?: throw CountryNotFoundException()
            spectacleDao.getSpectaclesOfCountry(country)
        }
    }

    fun getSpectacleOfCurAuthorLifePeriod(dateFrom: Timestamp, dateTo: Timestamp): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectaclesOfCurAuthorLifePeriod(dateFrom, dateTo)
        }
    }

    fun getSpectacleByName(name: String): Spectacle? {
        return transaction(dataSource) {
            spectacleDao.getSpectacleByName(name)
        }
    }

    fun updateSpectacle(spectacle: Spectacle) {
        transaction(dataSource) {
            spectacleDao.updateSpectacle(spectacle)
        }
    }

    fun updateSpectacle(id: Int, spectacleData: SpectacleData) {
        transaction(dataSource) {
            val genre = genreDao.getGenreByName(spectacleData.genreName) ?: throw GenreNotFoundException()
            var author: Author? = null
            if (spectacleData.authorId != null) {
                author = authorDao.getAuthor(spectacleData.authorId) ?: throw AuthorNotFoundException()
            }
            spectacleDao.updateSpectacle(Spectacle(id, spectacleData.name, genre,
                    spectacleData.ageCategory, author?.id))
        }
    }

    fun deleteSpectacle(spectacle: Spectacle) {
        transaction(dataSource) {
            spectacleDao.deleteSpectacle(spectacle)
        }
    }

    fun deleteSpectacle(spectacleData: SpectacleData) {
        transaction(dataSource) {
            val spectacle = getSpectacleByName(spectacleData.name) ?: throw SpectacleNotFoundException()
            spectacleDao.deleteSpectacle(spectacle)
        }
    }

    fun deleteSpectacle(spectacleId: Int) {
        transaction(dataSource) {
            spectacleDao.deleteSpectacle(spectacleId)
        }
    }
}