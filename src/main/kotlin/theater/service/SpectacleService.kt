package theater.service

import theater.dao.SpectacleDao
import theater.model.Author
import theater.model.Country
import theater.model.Genre
import theater.model.Spectacle
import java.sql.Date
import java.sql.Timestamp
import javax.sql.DataSource

class SpectacleService(private val dataSource: DataSource, private val spectacleDao: SpectacleDao) : Service() {

    fun createSpectacle(spectacle: Spectacle): Int {
        return transaction(dataSource) {
            spectacleDao.createSpectacle(spectacle)
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

    fun getSpectacleProfitByPeriod(spectacleId: Int, periodStart: Date, periodEnd: Date): Int {
        return transaction(dataSource) {
            spectacleDao.getSpectacleProfitByPeriod(spectacleId, periodStart, periodEnd)
        }
    }

    fun getSpectacleProfit(spectacleId: Int): Int {
        return transaction(dataSource) {
            spectacleDao.getSpectacleProfit(spectacleId)
        }
    }
}