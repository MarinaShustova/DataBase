package theater.controller

import org.springframework.web.bind.annotation.RestController
import theater.model.Country
import theater.service.CountryService

@RestController
class CountryController(private val countryService: CountryService) {

    fun createCountry(name: String): Int {
        return countryService.createCountry(Country(-1, name))
    }

}