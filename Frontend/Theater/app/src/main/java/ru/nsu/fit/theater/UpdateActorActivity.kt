package ru.nsu.fit.theater

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_update_actor.*
import ru.nsu.fit.theater.retrofit.model.ActorData

class UpdateActorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_actor)
        setSupportActionBar(toolbar)

        val data = ActorData(1, "Alena", "female", "1998-11-11", 
                2, 1000,"Russia", "2019-01-01", true)
        
        val fio = (findViewById(R.id.actor_update_name) as TextView)
        val birth = (findViewById(R.id.actor_update_birth) as TextView)
        val children =(findViewById(R.id.actor_update_children_amount) as TextView)
        val salary =(findViewById(R.id.actor_update_salary) as TextView)
        val hire =(findViewById(R.id.actor_update_hire) as TextView)
        val origin =(findViewById(R.id.actor_update_origin) as TextView)
        val sex =(findViewById(R.id.actor_update_sex) as TextView)
        val is_student =(findViewById(R.id.actor_update_student) as CheckBox)

        fio.text = data.fio 
        birth.text = data.birthDate 
        children.text =data.childrenAmount.toString() 
        salary.text =data.salary.toString() 
        hire.text = data.hireDate
        origin.text =data.origin 
        sex.text =data.sex 
        is_student.isChecked = data.isStudent

        val button = findViewById(R.id.actor_update_submit) as Button
        
        button.setOnClickListener {
            val fio = (findViewById(R.id.actor_update_name) as TextView).text.toString()
            val birth = (findViewById(R.id.actor_update_birth) as TextView).text.toString()
            val children =(findViewById(R.id.actor_update_children_amount) as TextView).text.toString().toInt()
            val salary =(findViewById(R.id.actor_update_salary) as TextView).text.toString().toInt()
            val hire =(findViewById(R.id.actor_update_hire) as TextView).text.toString()
            val origin =(findViewById(R.id.actor_update_origin) as TextView).text.toString()
            val sex =(findViewById(R.id.actor_update_sex) as TextView).text.toString()
            val is_student =(findViewById(R.id.actor_update_student) as CheckBox).isChecked

            val actor = ActorData(0, fio, sex, birth, children, salary, origin, hire, is_student)
            App.api.createActor(actor)
//            App.api.createActor()
        }

    }

}
