package ru.nsu.fit.theater.tickets_recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.ticket_item.view.*
import ru.nsu.fit.theater.model.Ticket

class TicketsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Ticket) {
        view.apply {
            ticket_row_number.text = item.row.toString()
            ticket_seat_number.text = item.seat.toString()
            ticket_price_number.text = item.price.toString()
        }
    }

}