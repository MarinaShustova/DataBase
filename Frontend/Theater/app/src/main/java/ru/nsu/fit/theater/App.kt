package ru.nsu.fit.theater

import android.app.Application
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.tickets.RetrofitTicketsController
import ru.nsu.fit.theater.retrofit.BackendApi

class App: Application() {
    companion object {
        private const val BASE_URL = "http://" + "192.168.1.42" + ":8080"

        lateinit var api: BackendApi

        val controllers = mapOf<IController.Type, IController>(
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