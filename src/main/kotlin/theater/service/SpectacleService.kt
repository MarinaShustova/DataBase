package theater.service

import theater.dao.AuthorDao
import theater.dao.GenreDao
import theater.dao.SpectacleDao
import theater.exception.GenreNotFoundException
import theater.model.Author
import theater.model.Country
import theater.model.Genre
import theater.model.Spectacle
import theater.model.data.SpectacleData
import java.sql.Timestamp
import javax.sql.DataSource

class SpectacleService(private val dataSource: DataSource,
                       private val spectacleDao: SpectacleDao,
                       private val genreDao: GenreDao,
                       private val authorDao: AuthorDao) : Service() {

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
                author = authorDao.getAuthor(spectacleData.authorId)
            }
            spectacleDao.createSpectacle(Spectacle(-1, spectacleData.name, genre, spectacleData.ageCategory))
        }
    }

    fun createAuthorOfSpectacle(spectacle: Spectacle, author: Author): Int {
        return transaction(dataSource) {
            spectacleDao.createAuthorOfSpectacle(spectacle, author)
        }
    }

    fun getSpectacle(id: Int): Spectacle? {
        return transaction(dataSource) {
            spectacleDao.getSpectacle(id)
        }
    }

    fun getSpectacleOfGenre(genre: Genre): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectacleOfGenre(genre)
        }
    }

    fun getSpectacleOfAuthor(author: Author): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectacleOfAuthor(author)
        }
    }

    fun getSpectacleOfCountry(country: Country): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectacleOfCountry(country)
        }
    }

    fun getSpectacleOfCurAuthorLifePeriod(dateFrom: Timestamp, dateTo: Timestamp): ArrayList<Spectacle> {
        return transaction(dataSource) {
            spectacleDao.getSpectacleOfCurAuthorLifePeriod(dateFrom, dateTo)
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

    fun deleteSpectacle(spectacle: Spectacle) {
        transaction(dataSource) {
            spectacleDao.deleteSpectacle(spectacle)
        }
    }

    fun deleteSpectacle(spectacleId: Int) {
        transaction(dataSource) {
            spectacleDao.deleteSpectacle(spectacleId)
        }
    }
}