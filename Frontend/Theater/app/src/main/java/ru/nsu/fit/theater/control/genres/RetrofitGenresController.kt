package ru.nsu.fit.theater.control.genres

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.GenreData

class RetrofitGenresController: IGenresController {
    override fun createGenre(name: String, callback: IGenresController.ICreateGenreCallback) {
        App.api.createGenre(name).enqueue(object : Callback<GenreData> {
            override fun onFailure(call: Call<GenreData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<GenreData>, response: Response<GenreData>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onGenreCreated(response.body()!!)
                }
            }
        })
    }

    override fun getGenre(id: Int, callback: IGenresController.IGetGenreCallback) {
        App.api.getGenre(id).enqueue(object : Callback<GenreData> {
            override fun onFailure(call: Call<GenreData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<GenreData>, response: Response<GenreData>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onGenreLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getGenres(callback: IGenresController.IGetGenresCallback) {
        App.api.getGenres().enqueue(object : Callback<List<GenreData>> {
            override fun onFailure(call: Call<List<GenreData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<GenreData>>, response: Response<List<GenreData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onGenresLoaded(response.body()!!)
                }
            }
        })
    }

    override fun updateGenre(data: GenreData, callback: IGenresController.IUpdateGenreCallback) {
        App.api.updateGenre(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onGenreUpdated()
                else callback.onError()
            }
        })
    }

    override fun deleteGenre(id: Int, callback: IGenresController.IDeleteGenreCallback) {
        App.api.deleteGenre(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onGenreDeleted()
                else callback.onError()
            }
        })
    }
}