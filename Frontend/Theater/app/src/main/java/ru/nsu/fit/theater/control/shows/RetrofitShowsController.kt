package ru.nsu.fit.theater.control.shows

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.model.PlaybillItem
import ru.nsu.fit.theater.retrofit.model.IdShowData
import ru.nsu.fit.theater.retrofit.model.ShowData

class RetrofitShowsController: IShowsController {
    override fun createShow(data: ShowData, callback: IShowsController.ICreateShowCallback) {
        App.api.createShow(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onShowCreated()
                else callback.onError()
            }
        })
    }

    override fun updateShow(id: Int, data: ShowData, callback: IShowsController.IUpdateShowCallback) {
        App.api.updateShow(id, data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onShowUpdated()
                else callback.onError()
            }
        })
    }

    override fun getShow(id: Int, callback: IShowsController.IGetShowCallback) {
        App.api.getShow(id).enqueue(object : Callback<IdShowData> {
            override fun onFailure(call: Call<IdShowData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<IdShowData>, response: Response<IdShowData>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onShowLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getShows(callback: IShowsController.IGetShowsCallback) {
        App.api.getShows().enqueue(object : Callback<List<IdShowData>> {
            override fun onFailure(call: Call<List<IdShowData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<IdShowData>>, response: Response<List<IdShowData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onShowsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun deleteShow(id: Int, callback: IShowsController.IDeleteShowCallback) {
        App.api.deleteShow(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onShowDeleted()
                else callback.onError()
            }
        })
    }

    override fun getPlaybills(callback: IShowsController.IGetPlaybillsCallback) {
        App.api.getPlaybills().enqueue(object : Callback<List<PlaybillItem>> {
            override fun onFailure(call: Call<List<PlaybillItem>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<PlaybillItem>>, response: Response<List<PlaybillItem>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onPlaybillsLoaded(response.body()!!)
                }
            }
        })
    }
}
