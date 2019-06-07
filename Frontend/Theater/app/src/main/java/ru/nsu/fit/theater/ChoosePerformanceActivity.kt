package ru.nsu.fit.theater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.genres.IGenresController
import ru.nsu.fit.theater.control.genres.RetrofitGenresController
import ru.nsu.fit.theater.control.spectacles.ISpectaclesController
import ru.nsu.fit.theater.control.spectacles.RetrofitSpectaclesController
import ru.nsu.fit.theater.model.Spectacle
import ru.nsu.fit.theater.retrofit.model.GenreData
import ru.nsu.fit.theater.retrofit.model.SpectacleData

class ChoosePerformanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_performance)
        var spectlist = ArrayList<String>()
        val spinner = findViewById<Spinner>(R.id.perfChooser)
        var genreD = ""
        var authorId : Int = -1
        val g = (App.controllers[IController.Type.SPECTACLES] as RetrofitSpectaclesController)
                .getSpectacles(object : ICallback, IGenresController.IGetGenresCallback, ISpectaclesController.IGetSpectaclesCallback {
                    override fun onGenresLoaded(genres: List<GenreData>) {
                    }

                    override fun onSpectaclesLoaded(spectacles: List<Spectacle>) {
                        for (gd : Spectacle in spectacles){
                            spectlist.add(gd.name)
                        }
                        if (spinner != null) {
                            val arrayAdapter = ArrayAdapter(this@ChoosePerformanceActivity,
                                    android.R.layout.simple_spinner_dropdown_item, spectlist)
                            spinner.adapter = arrayAdapter

                            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                    genreD = spectacles[position].name
                                    authorId = spectacles[position].id
                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                }
                            }
                        }
                    }

                    override fun onError() {
                    }

                })
    }
}
