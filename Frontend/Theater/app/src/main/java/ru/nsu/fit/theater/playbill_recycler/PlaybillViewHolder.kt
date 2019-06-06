package ru.nsu.fit.theater.playbill_recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.playbill_item.view.*
import ru.nsu.fit.theater.model.PlaybillItem
import java.text.SimpleDateFormat
import java.util.*

class PlaybillViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        val playbillDateDayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val playbillDateDayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val playbillTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    }

    fun bind(item: PlaybillItem) {
        view.apply {
            spectacle_date_day.text = playbillDateDayFormat.format(item.date)
            spectacle_date_weekday.text = playbillDateDayOfWeekFormat.format(item.date)
            spectacle_date_time.text = playbillTimeFormat.format(item.date)
            if(item.premiere){
                spectacle_is_premiere.text = "премьера"
            } else {
                spectacle_is_premiere.visibility = View.GONE
            }
            spectacle_name.text = item.name
            spectacle_age.text = item.age.toString() + "+"
        }
    }


}