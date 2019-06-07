package ru.nsu.fit.theater.view.playbill.playbill_recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.model.PlaybillItem

class PlaybillAdapter(private var items: List<PlaybillItem>) : RecyclerView.Adapter<PlaybillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaybillViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.playbill_item, parent, false)

        return PlaybillViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: PlaybillViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

}