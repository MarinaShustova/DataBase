package theater.controller

import org.springframework.web.bind.annotation.*
import theater.exception.CountryNotFoundException
import theater.model.Country
import theater.service.CountryService

@RestController
@RequestMapping("/country")
class CountryController(private val countryService: CountryService) {

    @PostMapping("/create")
    fun createCountry(@RequestParam name: String): Int {
        return countryService.createCountry(Country(-1, name))
    }

    @GetMapping("/{id}")
    fun getCountry(@PathVariable id: Int): Country {
        return countryService.getCountry(id) ?: throw CountryNotFoundException()
    }

    @GetMapping("/get")
    fun getCountries(): ArrayList<Country> {
        return countryService.getCountries()
    }

    @PostMapping("/update")
    fun updateCountry(@RequestBody country: Country) {
        countryService.updateCountry(country)
    }

//    @PostMapping("/delete")
//    fun deleteCountry(@RequestParam name: String) {
//        return countryService.deleteCountry(name)
//    }

}