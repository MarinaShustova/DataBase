package ru.nsu.fit.theater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.authors.IAuthorsController
import ru.nsu.fit.theater.control.authors.RetrofitAuthorsController
import ru.nsu.fit.theater.control.genres.IGenresController
import ru.nsu.fit.theater.control.genres.RetrofitGenresController
import ru.nsu.fit.theater.control.spectacles.ISpectaclesController
import ru.nsu.fit.theater.control.spectacles.RetrofitSpectaclesController
import ru.nsu.fit.theater.retrofit.model.AuthorData
import ru.nsu.fit.theater.retrofit.model.GenreData
import ru.nsu.fit.theater.retrofit.model.IdAuthorData
import ru.nsu.fit.theater.retrofit.model.SpectacleData

class CreateSpectacle : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_spectacle)
        var genrelist = ArrayList<String>()
        val authorsList = ArrayList<String>()
        val spinner = findViewById<Spinner>(R.id.genre)
        val spinner2 = findViewById<Spinner>(R.id.author)
        var spectacleData : SpectacleData
        var name : String
        var age : Int
        var genreD = ""
        var authorId : Int = -1

        val g = (App.controllers[IController.Type.GENRES] as RetrofitGenresController)
                .getGenres(object : ICallback, IGenresController.IGetGenresCallback {
                    override fun onError() {
                    }
                    override fun onGenresLoaded(genres: List<GenreData>) {
                        for (gd : GenreData in genres){
                            genrelist.add(gd.name)
                        }
                        if (spinner != null) {
                            val arrayAdapter = ArrayAdapter(this@CreateSpectacle,
                                    android.R.layout.simple_spinner_dropdown_item, genrelist)
                            spinner.adapter = arrayAdapter

                            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                    genreD = genres[position].name
                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                }
                            }
                        }

                    }
                })
        val a = (App.controllers[IController.Type.AUTHORS] as RetrofitAuthorsController)
                .getAuthors(object : ICallback, IAuthorsController.IGetAuthorsCallback {
                    override fun onAuthorsLoaded(authors: List<IdAuthorData>) {
                        for (ad : IdAuthorData in authors){
                            authorsList.add(ad.name+" "+ad.surname)
                        }
                        if (spinner2 != null) {
                            val arrayAdapter = ArrayAdapter(this@CreateSpectacle,
                                    android.R.layout.simple_spinner_dropdown_item, authorsList)
                            spinner2.adapter = arrayAdapter

                            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                    authorId = authors[position].id
//                                    authorId = 1
                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                }
                            }
                        }
                    }

                    override fun onError() {
                    }
                })
        val textName = findViewById<TextInputEditText>(R.id.spectacleName)
        val textAge = findViewById<TextInputEditText>(R.id.ageCategory)
        val submit = findViewById<Button>(R.id.createSpectacle)
        if (submit != null && textName != null && textAge != null) {
            submit.setOnClickListener {
                name = textName.text.toString()
                age = textAge.text.toString().toInt()
                spectacleData = SpectacleData(name, genreD, age, authorId)
                val q = (App.controllers[IController.Type.SPECTACLES] as RetrofitSpectaclesController)
                        .createSpectacle(spectacleData, object : ICallback, ISpectaclesController.ICreateSpectacleCallback{
                            override fun onSpectcleCreated() {
                                //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onError() {
                                //To change body of created functions use File | Settings | File Templates.
                            }

                        })
            }
        }

//        name = textName.toString()
//        age = textAge.toString().toInt()

    }

}
