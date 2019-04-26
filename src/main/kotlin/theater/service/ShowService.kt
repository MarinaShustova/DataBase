package theater.service

import theater.dao.ShowDao
import theater.model.Show
import javax.sql.DataSource

class ShowService(private val dataSource: DataSource, private val showDao: ShowDao) : Service() {

    fun createShow(show: Show): Int {
        return transaction(dataSource) {
            showDao.createShow(show)
        }
    }

    fun getShow(id: Int): Show? {
        return transaction(dataSource) {
            showDao.getShow(id)
        }
    }

    fun updateShow(show: Show) {
        transaction(dataSource) {
            showDao.updateShow(show)
        }
    }

    fun deleteShow(show: Show) {
        transaction(dataSource) {
            showDao.deleteShow(show)
        }
    }

    fun deleteShow(showId: Int) {
        transaction(dataSource) {
            showDao.deleteShow(showId)
        }
    }

}