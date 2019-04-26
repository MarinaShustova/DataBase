package theater.controller

import theater.model.Country
import theater.service.CountryService

class CountryController(private val countryService: CountryService) {

    fun createCountry(name: String): Int {
        return countryService.createCountry(Country(-1, name))
    }

}