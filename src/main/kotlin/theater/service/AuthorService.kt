package theater.service

import theater.dao.AuthorDao
import theater.model.Author
import theater.model.Country
import java.sql.Date
import javax.sql.DataSource

class AuthorService(private val dataSource: DataSource, private val authorDao: AuthorDao) : Service() {

    fun createAuthor(author: Author): Int {
        return transaction(dataSource) {
            authorDao.createAuthor(author)
        }
    }

    fun createAuthors(authors: Iterable<Author>): List<Int> {
        return transaction(dataSource) {
            authorDao.createAuthors(authors)
        }
    }

    fun getAuthor(id: Int): Author? {
        return transaction(dataSource) {
            authorDao.getAuthor(id)
        }
    }

    fun getAuthorByFullName(name: String, surname: String): Author? {
        return transaction(dataSource) {
            authorDao.getAuthorByFullName(name, surname)
        }
    }

    fun getAuthorsOfCountry(country: Country): ArrayList<Author> {
        return transaction(dataSource) {
            authorDao.getAuthorsOfCountry(country)
        }
    }

    fun getAuthorsOfCurCentury(startDate: Date, endDate: Date): ArrayList<Author> {
        return transaction(dataSource) {
            authorDao.getAuthorsOfCurTimePeriod(startDate, endDate)
        }
    }

    fun updateAuthor(author: Author) {
        return transaction(dataSource) {
            authorDao.updateAuthor(author)
        }
    }

    fun deleteAuthor(author: Author) {
        return authorDao.deleteAuthor(author)
    }
}