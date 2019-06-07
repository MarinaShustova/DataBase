package ru.nsu.fit.theater

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View


class ActorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor)
        createActorsRecycle()

        val button: View = findViewById(R.id.fab2)
        button.setOnClickListener {
            val intent = Intent(this, CreateActorActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createActorsRecycle() {
        supportFragmentManager.beginTransaction()
                .add(R.id.actors_container, ActorsFragment.newInstance())
                .addToBackStack(ActorsFragment.TAG)
                .commit()
    }

}
