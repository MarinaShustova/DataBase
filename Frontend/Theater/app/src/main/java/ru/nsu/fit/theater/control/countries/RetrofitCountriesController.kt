package ru.nsu.fit.theater.control.countries

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.CountryData

class RetrofitCountriesController: ICountriesController {
    override fun createCountry(name: String, callback: ICountriesController.ICreateCountryCallback) {
        App.api.createCountry(name).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onCountryCreated(response.body()!!)
                }
            }
        })
    }

    override fun getCountry(id: Int, callback: ICountriesController.IGetCountryCallback) {
        App.api.getCountry(id).enqueue(object : Callback<CountryData> {
            override fun onFailure(call: Call<CountryData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<CountryData>, response: Response<CountryData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onCountryLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getCountries(callback: ICountriesController.IGetCountriesCallback) {
        App.api.getCountries().enqueue(object : Callback<List<CountryData>> {
            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<CountryData>>, response: Response<List<CountryData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onCountriesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun updateCountry(data: CountryData, callback: ICountriesController.IUpdateCountryCallback) {
        App.api.updateCountry(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onCountryUpdated()
                else callback.onError()
            }
        })
    }
}