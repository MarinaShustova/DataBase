package ru.nsu.fit.theater.control.spectacles

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.SpectacleData
import java.sql.Timestamp

class RetrofitSpectaclesController: ISpectaclesController {
    override fun createSpectacle(data: SpectacleData, callback: ISpectaclesController.ICreateSpectacleCallback) {
        App.api.createSpectacle(data).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onSpectcleCreated()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

        })
    }

    override fun createAuthorOfSpectacle(authId: Int, specName: String, callback: ISpectaclesController.ICreateAuthorOfSpectacleCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectacle(id: Int, callback: ISpectaclesController.IGetSpectacleCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectacles(callback: ISpectaclesController.IGetSpectaclesCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectaclesOfGenre(genre: String, callback: ISpectaclesController.IGetSpectaclesOfGenreCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectaclesOfAuthor(id: Int, callback: ISpectaclesController.IGetSpectaclesOfAuthorCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectaclesOfCountry(name: String, callback: ISpectaclesController.IGetSpectaclesOfCountryCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectaclesOfCentury(cent: Int, callback: ISpectaclesController.IGetSpectaclesOfCenturyCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpectaclesOfPeriod(from: Timestamp, to: Timestamp, callback: ISpectaclesController.IGetSpectaclesOfPeriodCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateSpectacle(id: Int, data: SpectacleData, callback: ISpectaclesController.IUpdateSpectacleCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteSpectacle(id: Int, callback: ISpectaclesController.IDeleteSpectacleCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}