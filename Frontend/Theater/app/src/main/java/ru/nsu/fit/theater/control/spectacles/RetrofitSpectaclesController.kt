package ru.nsu.fit.theater.control.spectacles

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.IdSpectacleData
import ru.nsu.fit.theater.retrofit.model.SpectacleData
import java.sql.Timestamp

class RetrofitSpectaclesController: ISpectaclesController {
    override fun createSpectacle(data: SpectacleData, callback: ISpectaclesController.ICreateSpectacleCallback) {
        App.api.createSpectacle(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onSpectcleCreated()
                else callback.onError()
            }
        })
    }

    override fun createAuthorOfSpectacle(authId: Int, specName: String, callback: ISpectaclesController.ICreateAuthorOfSpectacleCallback) {
        App.api.createAuthorOfSpectacle(authId, specName).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onAuthorCreated()
                else callback.onError()
            }
        })
    }

    override fun getSpectacle(id: Int, callback: ISpectaclesController.IGetSpectacleCallback) {
        App.api.getSpectacle(id).enqueue(object : Callback<SpectacleData> {
            override fun onFailure(call: Call<SpectacleData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<SpectacleData>, response: Response<SpectacleData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectacleLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectacles(callback: ISpectaclesController.IGetSpectaclesCallback) {
        App.api.getSpectacles().enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectaclesOfGenre(genre: String, callback: ISpectaclesController.IGetSpectaclesOfGenreCallback) {
        App.api.getSpectaclesOfGenre(genre).enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectaclesOfAuthor(id: Int, callback: ISpectaclesController.IGetSpectaclesOfAuthorCallback) {
        App.api.getSpectaclesOfAuthor(id).enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectaclesOfCountry(name: String, callback: ISpectaclesController.IGetSpectaclesOfCountryCallback) {
        App.api.getSpectaclesOfCountry(name).enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectaclesOfCentury(cent: Int, callback: ISpectaclesController.IGetSpectaclesOfCenturyCallback) {
        App.api.getSpectaclesOfCentury(cent).enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getSpectaclesOfPeriod(from: Timestamp, to: Timestamp, callback: ISpectaclesController.IGetSpectaclesOfPeriodCallback) {
        App.api.getSpectaclesOfPeriod(from, to).enqueue(object : Callback<List<IdSpectacleData>> {
            override fun onFailure(call: Call<List<IdSpectacleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdSpectacleData>>, response: Response<List<IdSpectacleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onSpectaclesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun updateSpectacle(id: Int, data: SpectacleData, callback: ISpectaclesController.IUpdateSpectacleCallback) {
        App.api.updateSpectacle(id, data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onSpectacleUpdated()
                else callback.onError()
            }
        })
    }

    override fun deleteSpectacle(id: Int, callback: ISpectaclesController.IDeleteSpectacleCallback) {
        App.api.deleteSpectacle(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onSpectacleDeleted()
                else callback.onError()
            }
        })
    }
}