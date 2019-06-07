package ru.nsu.fit.theater.view.tickets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseActivity
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.tickets.ITicketsController
import ru.nsu.fit.theater.control.tickets.RetrofitTicketsController
import ru.nsu.fit.theater.retrofit.model.TicketData

class TicketsActivity : BaseActivity() {

    companion object {
        private val SHOW_ID_KEY = "get_show_id"

        fun getIntent(context: Context, showId: Int): Intent {
            val intent = Intent(context, TicketsActivity::class.java)
            intent.putExtra(SHOW_ID_KEY, showId)
            return intent
        }
    }

    private val controller = App.controllers[IController.Type.TICKETS] as RetrofitTicketsController
    private var showId: Int = -1

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
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
