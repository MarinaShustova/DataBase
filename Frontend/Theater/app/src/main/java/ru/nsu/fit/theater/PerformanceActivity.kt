package ru.nsu.fit.theater

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_performance.*
import kotlinx.android.synthetic.main.content_performance.*
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.producers.RetrofitProducersController
import ru.nsu.fit.theater.producers_recycler.ProducerAdapter

class PerformanceActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performance)
        setSupportActionBar(toolbar)

        val p = (App.controllers[IController.Type.PRODUCERS] as RetrofitProducersController)
        linearLayoutManager = LinearLayoutManager(this)
        perfaAtorsRecycler.layoutManager = linearLayoutManager
        perfProducersRecycler.layoutManager = linearLayoutManager
//        perfProducersRecycler.adapter = ProducerAdapter()
    }

}
