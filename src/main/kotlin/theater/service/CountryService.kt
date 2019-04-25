package theater.service

import theater.dao.AuthorDao
import theater.dao.CountryDao
import theater.model.Country
import javax.sql.DataSource

class CountryService(private val dataSource: DataSource, private val countryDao: CountryDao) : Service() {

    fun createCountry(country: Country): Int {
        return countryDao.createCountry(country)
    }

    fun getCountryByName(name: String): Country? {
        return countryDao.getCountryByName(name)
    }

    fun getCountry(id: Int): Country? {
        return countryDao.getCountry(id)
    }

    fun updateCountry(country: Country) {
        countryDao.updateCountry(country)
    }

    fun deleteCountry(country: Country) {
        countryDao.deleteCountry(country)
    }
}