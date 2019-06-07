package ru.nsu.fit.theater.producers_recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.model.Producer
import ru.nsu.fit.theater.retrofit.model.ProducerData

class ProducerAdapter(private var items: ArrayList<ProducerData>) : RecyclerView.Adapter<ProducerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProducerViewHolder {
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.perfproducer_row_item, p0, false)

        return ProducerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ProducerViewHolder, p1: Int) {
        p0.bind(items[p1])
         //To change body of created functions use File | Settings | File Templates.
    }
}