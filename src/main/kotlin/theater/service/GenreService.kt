package theater.service

import theater.dao.GenreDao
import theater.exception.GenreNotFoundException
import theater.model.Genre
import javax.sql.DataSource

class GenreService(private val dataSource: DataSource, private val genreDao: GenreDao) : Service() {

    fun createGenre(genre: Genre): Int {
        return genreDao.createGenre(genre)
    }

    fun getGenre(id: Int): Genre? {
        return genreDao.getGenre(id)
    }

    fun getGenres(): ArrayList<Genre> {
        return genreDao.getGenres()
    }

    fun getGenreByName(name: String): Genre? {
        return genreDao.getGenreByName(name)
    }

    fun updateGenre(genre: Genre) {
        genreDao.updateGenre(genre)
    }

    fun deleteGenre(name: String) {
        transaction(dataSource) {
            val genre = getGenreByName(name) ?: throw GenreNotFoundException()
            genreDao.deleteGenre(genre)
        }
    }

    fun deleteGenre(id: Int) {
        genreDao.deleteGenre(id)
    }

}