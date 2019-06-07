package ru.nsu.fit.theater

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_create_actor.*
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.actors.IActorController
import ru.nsu.fit.theater.control.actors.RetrofitActorsController
import ru.nsu.fit.theater.retrofit.model.ActorData

class CreateActorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_actor)
//        setSupportActionBar(toolbar)

        val button = findViewById(R.id.actor_input_submit) as Button

        button.setOnClickListener {
            val fio = (findViewById(R.id.actor_input_name) as TextView).text.toString()
            val sex =(findViewById(R.id.actor_input_sex) as TextView).text.toString()
            val birth = (findViewById(R.id.actor_input_birth) as TextView).text.toString()
            val children =(findViewById(R.id.actor_input_children_amount) as TextView).text.toString().toInt()
            val salary =(findViewById(R.id.actor_input_salary) as TextView).text.toString().toInt()
            val origin =(findViewById(R.id.actor_input_origin) as TextView).text.toString()
            val hire =(findViewById(R.id.actor_input_hire) as TextView).text.toString()
            val is_student =(findViewById(R.id.actor_input_student) as CheckBox).isChecked

            val actor = ActorData(0, fio, sex, birth, children, salary, origin, hire, is_student)
            val controller = App.controllers.get(IController.Type.ACTORS) as RetrofitActorsController
            controller.createActor(actor, object: IActorController.ICreateActorCallback {
                override fun onActorCreated() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError() {
                    Snackbar.make(it,"Failed to create actor", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            })
        }
    }

}
