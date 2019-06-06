package ru.nsu.fit.theater.control.actors

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.ActorData
import ru.nsu.fit.theater.retrofit.model.RoleData

interface IActorController: IController {
    interface IGetActorByIdCallback: ICallback {
        fun onActorLoaded(actor: ActorData)
    }

    interface IGetActorByNameCallback: ICallback {
        fun onActorLoaded(actor: ActorData)
    }

    interface ICreateActorCallback:ICallback {
        fun onActorCreated()
    }

    interface IDeleteActorCalback: ICallback {
        fun onActorDeleted()
    }

    interface IUpdateActorCallback: ICallback {
        fun onActorUpdated()
    }

    interface IGetActorsCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsBySexCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsByExpCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsByBirthCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsByAgeCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsByChildrenCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetActorsBySalaryCallback: ICallback {
        fun onActorsLoaded(actors: List<ActorData>)
    }

    interface IGetRankedActorsCallback: ICallback {
        fun onActorsLoaded(actors: Pair<List<ActorData>, Int>)
    }

    interface IGetRankedActorsBySexCallback: ICallback {
        fun onActorsLoaded(actors: Pair<List<ActorData>, Int>)
    }

    interface IGetRankedActorsByAgeCallback: ICallback {
        fun onActorsLoaded(actors: Pair<List<ActorData>, Int>)
    }

    interface IGetRankedActorsByContestsCallback: ICallback {
        fun onActorsLoaded(actors: Pair<List<ActorData>, Int>)
    }

    interface IGetActorRolesByAgeCallback: ICallback {
        fun onRolesLoaded(roles: List<RoleData>)
    }

    interface IGetActorRolesByProducerCallback: ICallback {
        fun onRolesLoaded(roles: List<RoleData>)
    }

    interface IGetActorRolesByPeriodCallback: ICallback {
        fun onRolesLoaded(roles: List<RoleData>)
    }

    interface IGetActorRolesByGenreCallback: ICallback {
        fun onRolesLoaded(roles: List<RoleData>)
    }

    interface IGetActorRolesCallback: ICallback {
        fun onRolesLoaded(roles: List<RoleData>)
    }

    fun getActorById(id: Int, callback: IGetActorByIdCallback)

    fun getActorByName(fio: String, callback: IGetActorByNameCallback)

    fun createActor(data: ActorData, callback: ICreateActorCallback)

    fun deleteActor(id: Int, callback: IDeleteActorCalback)

    fun updateActor(data: ActorData, callback: IUpdateActorCallback)

    fun getActors(callback: IGetActorsCallback)

    fun getActorsBySex(sex: String, callback: IGetActorsBySexCallback)

    fun getActorsByExp(exp: Int, callback: IGetActorsByExpCallback)

    fun getActorsByBirth(birth: String, callback: IGetActorsByBirthCallback)

    fun getActorsByAge(age: Int, callback: IGetActorsByAgeCallback)

    fun getActorsByChildrenCallback(amount: Int, callback: IGetActorsByChildrenCallback)

    fun getActorsBySalary(salary: Int, callback: IGetActorsBySalaryCallback)

    fun getRankedActors(callback: IGetRankedActorsCallback)

    fun getRankedActorsBySex(sex: String, callback: IGetRankedActorsBySexCallback)

    fun getRankedActorsByAge(age: Int, callback: IGetRankedActorsByAgeCallback)

    fun getRankedActorsByContests(
            contests: List<String>,
            callback: IGetRankedActorsByContestsCallback
    )

    fun getActorRoles(id: Int, callback: IGetActorRolesCallback)

    fun getActorRolesByAge(id: Int, age: Int, callback: IGetActorRolesByAgeCallback)

    fun getActorRolesByProducer(
            id: Int,
            producerFio: String,
            callback: IGetActorRolesByProducerCallback
    )

    fun getActorRolesByPeriod(
            id: Int,
            from: String,
            to: String,
            callback: IGetActorRolesByPeriodCallback
    )

    fun getActorRolesByGenre(id: Int, genre: String, callback: IGetActorRolesByGenreCallback)
}