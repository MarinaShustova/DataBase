package ru.nsu.fit.theater.control.tickets

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.TicketData

class RetrofitTicketsController: ITicketsController {
    override fun getTicketsOfShow(showId: Int, callback: ITicketsController.IGetTicketsOfShowCallback) {
        App.api.getTicketsOfShow(showId).enqueue(object : Callback<List<TicketData>> {
            override fun onFailure(call: Call<List<TicketData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<TicketData>>, response: Response<List<TicketData>>) {
                if (response.body() == null || !response.isSuccessful){
                    callback.onError()
                } else {
                    callback.onTicketsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun buyTicket(
            row: Int,
            seat: Int,
            price: Int,
            showId: Int,
            previously: Boolean,
            callback: ITicketsController.IBuyTicketCallback
    ) {
        App.api.buyTicket(row, seat, price, showId, previously).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) callback.onTicketBought()
                else callback.onError()
            }
        })
    }
}