package ru.nsu.fit.theater.actors_recycle

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.actors_recycler.ActorsViewHolder
import ru.nsu.fit.theater.retrofit.model.ActorData

class ActorsAdapter(private var items: ArrayList<ActorData>): RecyclerView.Adapter<ActorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.actor_item, parent, false)

        return ActorsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ActorsViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }


}