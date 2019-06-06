package ru.nsu.fit.theater.control.countries

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.CountryData

interface ICountriesController: IController {
    interface ICreateCountryCallback: ICallback {
        fun onCountryCreated()
    }

    interface IGetCountryCallback: ICallback {
        fun onCountryLoaded(country: CountryData)
    }

    interface IGetCountriesCallback: ICallback {
        fun onCountriesLoaded(countries: List<CountryData>)
    }

    interface IUpdateCountryCallback: ICallback {
        fun onCountryUpdated()
    }

    fun createCountry(name: String, callback: ICreateCountryCallback)

    fun getCountry(id: Int, callback: IGetCountryCallback)

    fun getCountries(callback: IGetCountriesCallback)

    fun updateCountry(data: CountryData, callback: IUpdateCountryCallback)
}