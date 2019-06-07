package ru.nsu.fit.theater

import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import ru.nsu.fit.theater.actors_recycle.ActorsAdapter
import ru.nsu.fit.theater.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_actors.*
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.control.actors.IActorController
import ru.nsu.fit.theater.control.actors.RetrofitActorsController
import ru.nsu.fit.theater.retrofit.model.ActorData

class ActorsFragment: BaseFragment() {

    companion object {
        val TAG = "ActorsFragment"
        fun newInstance(): BaseFragment {
            return ActorsFragment()
        }
    }

    fun onCreate(){
        val controller = App.controllers.get(IController.Type.ACTORS) as RetrofitActorsController
        controller.getActors(object: IActorController.IGetActorsCallback {
            override fun onError() {
            }

            override fun onActorsLoaded(a: List<ActorData>) {
                actors = ArrayList(a)
                println(actors)
                adapter = ActorsAdapter(actors)
                configRecycler()
            }
        })
    }

    override val layoutId: Int
        get() = R.layout.fragment_actors

    private lateinit var actors: ArrayList<ActorData>
    private lateinit var adapter: ActorsAdapter

    override fun onResume() {
        super.onResume()
//TODO: uncomment this section
        val controller = App.controllers.get(IController.Type.ACTORS) as RetrofitActorsController
        controller.getActors(object: IActorController.IGetActorsCallback {
            override fun onError() {
            }

            override fun onActorsLoaded(a: List<ActorData>) {
                actors = ArrayList(a)
                println(actors)
                adapter = ActorsAdapter(actors)
                configRecycler()
            }
        })

//        actors = initData()
//        adapter = ActorsAdapter(actors)
//        configRecycler()
    }


    fun initData(): ArrayList<ActorData> {
        val persons = ArrayList<ActorData>()
        persons.add(ActorData(0,"Лариса Гузеева", "female", "1965-12-02", 1, 1000, "Россия", "2019-06-07",false ))
        persons.add(ActorData(0,"Владимир Зеленский", "male", "1965-12-02", 1, 1000, "Украина", "2019-06-07",false ))
        persons.add(ActorData(0,"Evan Peters", "male", "1985-12-02", 1, 1000, "USA", "2019-06-07",true ))
        return persons
    }

//    override fun onStart() {
//        super.onStart()
//
//        playbill = controller.getSpectacles()
//        adapter = PlaybillAdapter(playbill)
//        configRecycler()
//    }

    private fun configRecycler() {
        actors_recycler_view.layoutManager = LinearLayoutManager(activity)
        actors_recycler_view.adapter = adapter
    }
}