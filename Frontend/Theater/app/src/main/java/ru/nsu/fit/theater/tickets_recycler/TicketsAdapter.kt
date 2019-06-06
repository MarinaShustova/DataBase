package ru.nsu.fit.theater.tickets_recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.model.Ticket

class TicketsAdapter(private var items: ArrayList<Ticket>) : RecyclerView.Adapter<TicketsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TicketsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ticket_item, parent, false)

        return TicketsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: TicketsViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun addItem(item: Ticket) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}