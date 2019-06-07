package ru.nsu.fit.theater

import android.app.Application
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.actors.RetrofitActorsController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.control.countries.RetrofitCountriesController
import ru.nsu.fit.theater.control.employees.RetrofitEmployeesController
import ru.nsu.fit.theater.control.genres.RetrofitGenresController
import ru.nsu.fit.theater.control.shows.RetrofitShowsController
import ru.nsu.fit.theater.control.spectacles.RetrofitSpectaclesController
import ru.nsu.fit.theater.control.tickets.RetrofitTicketsController
import ru.nsu.fit.theater.retrofit.BackendApi

class App: Application() {
    companion object {
        private const val BASE_URL = "http://" + "10.9.41.28" + ":8080"

        lateinit var api: BackendApi

        val controllers = mapOf(
                Pair(IController.Type.ACTORS, RetrofitActorsController()),
                Pair(IController.Type.AUTHORS, RetrofitAuthorsController()),
                Pair(IController.Type.COUNTRIES, RetrofitCountriesController()),
                Pair(IController.Type.EMPLOYEES, RetrofitEmployeesController()),
                Pair(IController.Type.GENRES, RetrofitGenresController()),
                Pair(IController.Type.SHOWS, RetrofitShowsController()),
                Pair(IController.Type.SPECTACLES, RetrofitSpectaclesController()),
                Pair(IController.Type.TICKETS, RetrofitTicketsController())
        )
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
                .build()
        api = retrofit.create(BackendApi::class.java)
    }
}