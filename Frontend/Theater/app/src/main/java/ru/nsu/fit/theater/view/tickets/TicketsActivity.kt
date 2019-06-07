package ru.nsu.fit.theater.view.tickets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ticket.*
import kotlinx.android.synthetic.main.ticket_item.view.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseActivity
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.tickets.ITicketsController
import ru.nsu.fit.theater.control.tickets.RetrofitTicketsController
import ru.nsu.fit.theater.retrofit.model.TicketData

class TicketsActivity : BaseActivity() {

    companion object {
        val SHOW_ID_KEY = "get_show_id"

        fun getIntent(context: Context, showId: Int): Intent {
            val intent = Intent(context, TicketsActivity::class.java)
            intent.putExtra(SHOW_ID_KEY, showId)
            return intent
        }
    }

    private val controller = App.controllers[IController.Type.TICKETS] as RetrofitTicketsController
    private var showId: Int = -1

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        showId = intent.getIntExtra(SHOW_ID_KEY, -1)
        if (showId == -1) {
            showErrorScreen("Не выбрано представление")
        }
    }

    override fun onResume() {
        super.onResume()

        if (showId == -1) {
            showErrorScreen("Не выбрано представление")
        }
        controller.getTicketsOfShow(showId, object : ITicketsController.IGetTicketsOfShowCallback {
            override fun onTicketsLoaded(tickets: List<TicketData>) {
                val adapter = TicketsAdapter(tickets.toMutableList())
                tickets_recycler_view.layoutManager = LinearLayoutManager(context)
                tickets_recycler_view.adapter = adapter
            }

            override fun onError() {
                showErrorScreen("Ошибка загрузки данных о билетах")

            }
        })
    }

    inner class TicketsAdapter(private var items: MutableList<TicketData>) : RecyclerView.Adapter<TicketsViewHolder>() {
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

        fun addItem(item: TicketData) {
            items.add(item)
            notifyItemInserted(items.lastIndex)
        }

        fun removeItemAt(position: Int) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class TicketsViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
        private lateinit var item: TicketData

        override fun onClick(v: View?) {
            val c = (App.controllers[IController.Type.TICKETS] as RetrofitTicketsController)
            c.buyTicket(
                    item.row,
                    item.seat,
                    item.price,
                    item.showId,
                    item.previously,
                    object : ITicketsController.IBuyTicketCallback {
                        override fun onTicketBought() {
                            Toast.makeText(context, "Билет куплен", Toast.LENGTH_SHORT).show()
                            onResume()
                        }

                        override fun onError() {
                            Toast.makeText(context, "Ошибка: билет не куплен", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
        }

        init {
            view.buy_ticket_button.setOnClickListener(this)
        }

        fun bind(item: TicketData) {
            this.item = item
            view.apply {
                ticket_row_number.text = item.row.toString()
                ticket_seat_number.text = item.seat.toString()
                ticket_price_number.text = item.price.toString()
            }
        }
    }
}
