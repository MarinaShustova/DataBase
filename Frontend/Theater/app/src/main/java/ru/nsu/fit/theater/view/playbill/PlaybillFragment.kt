package ru.nsu.fit.theater.view.playbill

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_playbill.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.R
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.shows.IShowsController
import ru.nsu.fit.theater.control.shows.RetrofitShowsController
import ru.nsu.fit.theater.model.PlaybillItem
import ru.nsu.fit.theater.view.playbill.playbill_recycler.PlaybillAdapter

class PlaybillFragment : BaseFragment() {

    companion object {
        val TAG = "PlaybillFragment"
        fun newInstance(): BaseFragment {
            return PlaybillFragment()
        }

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

}