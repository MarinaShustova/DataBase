package ru.nsu.fit.theater

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_playbill.*
import ru.nsu.fit.theater.base.BaseFragment
import ru.nsu.fit.theater.controller.PlaybillController
import ru.nsu.fit.theater.model.PlaybillItem
import ru.nsu.fit.theater.playbill_recycler.PlaybillAdapter

class PlaybillFragment : BaseFragment() {

    companion object {
        val TAG = "PlaybillFragment"
        fun newInstance(): BaseFragment {
            return PlaybillFragment()
        }

    }

    override val layoutId: Int
        get() = R.layout.fragment_playbill

    private lateinit var playbill: ArrayList<PlaybillItem>
    private lateinit var adapter: PlaybillAdapter

    private val controller = PlaybillController()

    override fun onResume() {
        super.onResume()

        playbill = controller.getSpectacles()
        adapter = PlaybillAdapter(playbill)
        configRecycler()
    }

//    override fun onStart() {
//        super.onStart()
//
//        playbill = controller.getSpectacles()
//        adapter = PlaybillAdapter(playbill)
//        configRecycler()
//    }

    private fun configRecycler() {
        playbill_recycler_view.layoutManager = LinearLayoutManager(activity)
//        playbill_recycler_view.addItemDecoration(
//                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
//        )
        playbill_recycler_view.adapter = adapter

    }

}