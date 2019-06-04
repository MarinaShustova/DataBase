package theater.service

import theater.dao.AuthorDao
import theater.dao.CountryDao
import theater.exception.AuthorNotFoundException
import theater.exception.CountryNotFoundException
import theater.model.Author
import theater.model.Country
import theater.model.data.AuthorData
import java.sql.Date
import javax.sql.DataSource

class AuthorService(private val dataSource: DataSource, private val authorDao: AuthorDao,
                    private val countryDao: CountryDao) : Service() {

    fun createAuthor(authorData: AuthorData): Int {
        return transaction(dataSource) {
            val country = countryDao.getCountryByName(authorData.countryName) ?: throw CountryNotFoundException()
            val author = Author(-1, authorData.name, authorData.surname,
                    authorData.birthDate, authorData.deathDate, country)
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

    fun getAuthorsOfCountry(countryName: String): ArrayList<Author> {
        return transaction(dataSource) {
            val country = countryDao.getCountryByName(countryName) ?: throw CountryNotFoundException()
            authorDao.getAuthorsOfCountry(country)
        }
    }

    fun getAuthorsOfCurCentury(startDate: Date, endDate: Date): ArrayList<Author> {
        return transaction(dataSource) {
            authorDao.getAuthorsOfCurTimePeriod(startDate, endDate)
        }
    }

    fun updateAuthor(authorData: AuthorData) {
        return transaction(dataSource) {
            val country = countryDao.getCountryByName(authorData.countryName) ?: throw CountryNotFoundException()
            val author = Author(-1, authorData.name, authorData.surname,
                    authorData.birthDate, authorData.deathDate, country)
            authorDao.updateAuthor(author)
        }
    }

    fun deleteAuthor(authorData: AuthorData): Author {
        return transaction(dataSource) {
            val author = getAuthorByFullName(authorData.name, authorData.surname) ?: throw AuthorNotFoundException()
            authorDao.deleteAuthor(author.id)
            author
        }
    }

    fun deleteAuthor(id: Int) {
        return transaction(dataSource) {
            authorDao.deleteAuthor(id)
        }
    }
}