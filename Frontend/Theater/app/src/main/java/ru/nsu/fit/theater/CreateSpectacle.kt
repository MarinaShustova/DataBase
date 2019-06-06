package ru.nsu.fit.theater

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.nav_header_main.*
import retrofit2.http.GET
import ru.nsu.fit.theater.retrofit.model.GenreData
import java.lang.ref.WeakReference

class CreateSpectacle : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_spectacle)

        val task = MyAsyncTask()
        val genres = task.execute().get()

//        val g = App.api.getGenres().enqueue()
        val spinner = findViewById<Spinner>(R.id.genre)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }

    }

    companion object {
        class MyAsyncTask internal constructor() : AsyncTask<Void, Void, List<GenreData>?>() {

            private var resp: List<GenreData>? = null

            override fun doInBackground(vararg p0: Void?): List<GenreData>? {
                try {
                    val genres = App.api.getGenres().execute().body()
                    resp = genres
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return resp
            }
        }
    }
}
