package ru.nsu.fit.theater.view.playbill

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_playbill.*
import kotlinx.android.synthetic.main.playbill_item.view.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.base.IAuthorFragmentListener
import ru.nsu.fit.theater.base.IShowFragmentListener
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.shows.IShowsController
import ru.nsu.fit.theater.control.shows.RetrofitShowsController
import ru.nsu.fit.theater.model.PlaybillItem
import java.text.SimpleDateFormat
import java.util.*

class PlaybillFragment : BaseFragment() {

    companion object {
        val TAG = "PlaybillFragment"
        fun newInstance(): BaseFragment {
            return PlaybillFragment()
        }

    }

    private val listener by lazy {
        context as? IShowFragmentListener
                ?: throw Exception("Activity must implement " +
                        "IAuthorFragmentListener")
    }

    override val layoutId: Int
        get() = R.layout.fragment_playbill

    private lateinit var playbill: List<PlaybillItem>
    private lateinit var adapter: PlaybillAdapter

    private val controller = App.controllers[IController.Type.SHOWS] as RetrofitShowsController

    override fun onResume() {
        super.onResume()

        controller.getPlaybills(object : IShowsController.IGetPlaybillsCallback {
            override fun onPlaybillsLoaded(playbills: List<PlaybillItem>) {
                playbill = playbills
                adapter = PlaybillAdapter(playbill)
                configRecycler()
            }

            override fun onError() {
                System.err.println("Error while loading playbill")
            }
        })
    }

    private fun configRecycler() {
        playbill_recycler_view.layoutManager = LinearLayoutManager(activity)
//        playbill_recycler_view.addItemDecoration(
//                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        )
        playbill_recycler_view.adapter = adapter

    }

    inner class PlaybillViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener {
        lateinit var item: PlaybillItem

        private val playbillDateDayFormat = SimpleDateFormat("dd", Locale.getDefault())
        private val playbillDateDayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
        private val playbillTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        init {
            view.product_view_button_save.setOnClickListener(this)
        }

        fun bind(item: PlaybillItem) {
            this.item = item
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

        override fun onClick(v: View?) {
            listener.openShowTickets(item.showId)
        }
    }

    inner class PlaybillAdapter(private var items: List<PlaybillItem>) : RecyclerView.Adapter<PlaybillViewHolder>() {
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

}