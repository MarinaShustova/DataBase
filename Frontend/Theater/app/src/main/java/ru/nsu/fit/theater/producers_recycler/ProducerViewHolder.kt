package ru.nsu.fit.theater.producers_recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.perfproducer_row_item.view.*
import kotlinx.android.synthetic.main.playbill_item.view.*
import ru.nsu.fit.theater.model.PlaybillItem
import ru.nsu.fit.theater.model.Producer
import ru.nsu.fit.theater.retrofit.model.ProducerData
import java.text.SimpleDateFormat
import java.util.*

class ProducerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ProducerData) {
        view.apply {
            prodText.text = item.fio
            prodBirth.text = SimpleDateFormat("YYYY-MM-dd", Locale.getDefault()).format(item.birthDate)
            prodActivity.text = item.activity
            prodCountry.text = item.origin
        }
    }
}