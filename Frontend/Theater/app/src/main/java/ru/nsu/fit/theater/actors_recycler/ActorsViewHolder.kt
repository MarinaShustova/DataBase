package ru.nsu.fit.theater.actors_recycler

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import ru.nsu.fit.theater.retrofit.model.ActorData
import kotlinx.android.synthetic.main.actor_item.view.*
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.UpdateActorActivity
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.actors.IActorController
import ru.nsu.fit.theater.control.actors.RetrofitActorsController

class ActorsViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(item: ActorData) {
        view.apply {
            actor_output_name.text = item.fio
            actor_output_origin.text = item.origin
            actor_output_birth.text = item.birthDate
            actor_output_hire.text = item.hireDate
            if (item.isStudent) actor_output_student.text = "Студент"
            actor_edit_button.setOnClickListener {
                val intent = Intent(view.context, UpdateActorActivity::class.java)
                intent.putExtra("id", item.id)
                intent.putExtra("fio" ,item.fio)
                intent.putExtra("sex" ,item.sex)
                intent.putExtra("birth" ,item.birthDate)
                intent.putExtra("children" ,item.childrenAmount)
                intent.putExtra("salary" ,item.salary)
                intent.putExtra("origin" ,item.origin)
                intent.putExtra("hire" ,item.hireDate)
                intent.putExtra("student" ,item.isStudent)
                view.context.startActivity(intent)
            }
            actor_delete_button.setOnClickListener {
                val controller = App.controllers.get(IController.Type.ACTORS) as RetrofitActorsController
                controller.deleteActor(item.id, object: IActorController.IDeleteActorCalback {
                    override fun onActorDeleted() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError() {
                        Snackbar.make(it,"Failed to delete actor", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    }
                })
            }
        }
    }


}