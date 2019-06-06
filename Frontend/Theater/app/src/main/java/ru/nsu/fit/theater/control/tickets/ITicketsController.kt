package ru.nsu.fit.theater.control.tickets

import ru.nsu.fit.theater.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.TicketData

interface ITicketsController: IController {
    interface IGetTicketsOfShowCallback: ICallback {
        fun onTicketsLoaded(tickets: List<TicketData>)
    }

    interface IBuyTicketCallback: ICallback {
        fun onTicketBought()
    }

    fun getTicketsOfShow(showId: Int, callback: IGetTicketsOfShowCallback)

    fun buyTicket(
            row: Int,
            seat: Int,
            price: Int,
            showId: Int,
            previously: Boolean,
            callback: IBuyTicketCallback)
}