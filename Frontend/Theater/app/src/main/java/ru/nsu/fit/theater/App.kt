package ru.nsu.fit.theater

import android.app.Application
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.control.genres.RetrofitGenresController
import ru.nsu.fit.theater.control.spectacles.RetrofitSpectaclesController
import ru.nsu.fit.theater.control.tickets.RetrofitTicketsController
import ru.nsu.fit.theater.retrofit.BackendApi

class App: Application() {
    companion object {
        private const val BASE_URL = "http://" + "192.168.1.42" + ":8080"

        lateinit var api: BackendApi

        val controllers = mapOf<IController.Type, IController>(
            Pair(IController.Type.TICKETS, RetrofitTicketsController()),
                Pair(IController.Type.GENRES, RetrofitGenresController()),
                Pair(IController.Type.AUTHORS, RetrofitAuthorsController()),
                Pair(IController.Type.SPECTACLES, RetrofitSpectaclesController())
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