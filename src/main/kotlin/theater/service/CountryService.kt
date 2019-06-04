package theater.service

import theater.dao.CountryDao
import theater.exception.CountryNotFoundException
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

    fun deleteCountry(name: String) {
        return transaction(dataSource) {
            val country = getCountryByName(name) ?: throw CountryNotFoundException()
            countryDao.deleteCountry(country.id)
        }
    }

    fun deleteCountry(id: Int) {
        countryDao.deleteCountry(id)
    }
}