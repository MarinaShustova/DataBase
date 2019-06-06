package ru.nsu.fit.theater

import android.app.Application
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.nsu.fit.theater.retrofit.BackendApi

class App: Application() {
    companion object {
        private const val BASE_URL = "http://" + "localhost" + ":8080"

        lateinit var api: BackendApi
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