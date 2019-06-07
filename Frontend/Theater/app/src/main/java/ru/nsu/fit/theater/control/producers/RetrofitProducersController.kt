package ru.nsu.fit.theater.control.producers

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.ProducerData

class RetrofitProducersController : IProducerController {
    override fun getProducerById(id: Int, callback: IProducerController.IgetProducerByIdCallback) {
        App.api.getProducerById(id).enqueue(object : Callback<ProducerData>{
            override fun onFailure(call: Call<ProducerData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ProducerData>, response: Response<ProducerData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onProducerLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getProducerByFio(fio: String, callback: IProducerController.IgetProducerByFioCallback) {
        App.api.getProducerByFio(fio).enqueue(object : Callback<ProducerData> {
            override fun onFailure(call: Call<ProducerData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ProducerData>, response: Response<ProducerData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onProducerLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getProducers(callback: IProducerController.IgetProducersCallback) {
        App.api.getProducers().enqueue(object  : Callback<List<ProducerData>> {
            override fun onFailure(call: Call<List<ProducerData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ProducerData>>, response: Response<List<ProducerData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onProducersLoaded(response.body()!!)
                }
            }
        })
    }

    override fun createProducer(data: ProducerData, callback: IProducerController.ICreateProducerCallback) {
        App.api.createProducer(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onProducerCreated()
                else callback.onError()
            }
        })
    }

    override fun deleteProducer(id: Int, callback: IProducerController.IDeleteProducerCallback) {
        App.api.deleteProducer(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onProducerDeleted()
                else callback.onError()
            }
        })
    }

    override fun updateProducer(data: ProducerData, callback: IProducerController.IUpdateProducerCallback) {
        App.api.updateProducer(data).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onProducerUpdated()
                else callback.onError()
            }
        })
    }

}