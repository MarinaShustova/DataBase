package theater.service

import theater.dao.GenreDao
import theater.model.Genre
import javax.sql.DataSource

class GenreService(private val dataSource: DataSource, private val genreDao: GenreDao) : Service() {

    fun createGenre(genre: Genre): Int {
        return genreDao.createGenre(genre)
    }

    fun getGenre(id: Int): Genre? {
        return genreDao.getGenre(id)
    }

    fun getGenreByName(name: String): Genre? {
        return genreDao.getGenreByName(name)
    }

    fun updateGenre(genre: Genre) {
        genreDao.updateGenre(genre)
    }

    fun deleteGenre(genre: Genre) {
        genreDao.deleteGenre(genre)
    }

}