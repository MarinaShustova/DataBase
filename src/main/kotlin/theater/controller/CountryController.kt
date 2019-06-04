package theater.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import theater.model.Country
import theater.service.CountryService

@RestController
@RequestMapping("/country")
class CountryController(private val countryService: CountryService) {

    @PostMapping("/create")
    fun createCountry(@RequestParam name: String): Int {
        return countryService.createCountry(Country(-1, name))
    }

    @PostMapping("/delete")
    fun deleteCountry(@RequestParam name: String) {
        return countryService.deleteCountry(name)
    }

}