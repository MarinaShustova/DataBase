package ru.nsu.fit.theater.control.authors

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.AuthorData
import ru.nsu.fit.theater.retrofit.model.IdAuthorData

class RetrofitAuthorsController: IAuthorsController {
    override fun createAuthor(data: AuthorData, callback: IAuthorsController.ICreateAuthorCallback) {
        App.api.createAuthor(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onAuthorCreated()
                else callback.onError()
            }
        })
    }

    override fun getAuthor(id: Int, callback: IAuthorsController.IGetAuthorCallback) {
        App.api.getAuthorById(id).enqueue(object : Callback<IdAuthorData> {
            override fun onFailure(call: Call<IdAuthorData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<IdAuthorData>, response: Response<IdAuthorData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onAuthorLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getAuhtors(callback: IAuthorsController.IGetAuthorsCallback) {
        App.api.getAuthors().enqueue(object : Callback<List<IdAuthorData>> {
            override fun onFailure(call: Call<List<IdAuthorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdAuthorData>>, response: Response<List<IdAuthorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onAuthorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getAuthorsOfCountry(name: String, callback: IAuthorsController.IGetAuthorsOfCountryCallback) {
        App.api.getAuthorsOfCountry(name).enqueue(object : Callback<List<IdAuthorData>> {
            override fun onFailure(call: Call<List<IdAuthorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdAuthorData>>, response: Response<List<IdAuthorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onAuthorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getAuthorsOfCentury(century: Int, callback: IAuthorsController.IGetAuthorsOfCenturyCallback) {
        App.api.getAuthorsOfCentury(century).enqueue(object : Callback<List<IdAuthorData>> {
            override fun onFailure(call: Call<List<IdAuthorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdAuthorData>>, response: Response<List<IdAuthorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onAuthorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun updateAuthor(id: Int, data: AuthorData, callback: IAuthorsController.IUpdateAuthorCallback) {
        App.api.updateAuthor(id, data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onAuthorUpdated()
                else callback.onError()
            }
        })
    }

    override fun deleteAuthor(id: Int, callback: IAuthorsController.IDeleteAuthorCallback) {
        App.api.deleteAuthor(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onAuthorDeleted()
                else callback.onError()
            }
        })
    }
}