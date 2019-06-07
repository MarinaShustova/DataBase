package theater.service

import theater.dao.ShowDao
import theater.model.Show
import theater.model.data.PlaybillData
import theater.model.data.ShowData
import javax.sql.DataSource

class ShowService(private val dataSource: DataSource,
                  private val showDao: ShowDao) : Service() {

    fun createShow(show: Show): Int {
        return transaction(dataSource) {
            showDao.createShow(show)
        }
    }

    fun createShow(showData: ShowData): Int {
        return transaction(dataSource) {
            showDao.createShow(Show(-1, showData.date, showData.premiere, showData.performanceId))
        }
    }

    fun getShow(id: Int): Show? {
        return transaction(dataSource) {
            showDao.getShow(id)
        }
    }

    fun getShows(): ArrayList<Show> {
        return transaction(dataSource) {
            showDao.getShows()
        }
    }

    fun updateShow(show: Show) {
        transaction(dataSource) {
            showDao.updateShow(show)
        }
    }

    fun updateShow(id: Int, showData: ShowData) {
        transaction(dataSource) {
            showDao.updateShow(Show(id, showData.date, showData.premiere, showData.performanceId))
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

    fun getPlaybills(): List<PlaybillData> {
        return transaction(dataSource) {
            showDao.getPlaybills()
        }
    }
}