package ru.nsu.fit.theater.control.actors

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.ActorData
import ru.nsu.fit.theater.retrofit.model.RoleData

class RetrofitActorsController: IActorController {
    override fun getActorById(id: Int, callback: IActorController.IGetActorByIdCallback) {
        App.api.getActorById(id).enqueue(object : Callback<ActorData> {
            override fun onFailure(call: Call<ActorData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ActorData>, response: Response<ActorData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorByName(fio: String, callback: IActorController.IGetActorByNameCallback) {
        App.api.getActorByName(fio).enqueue(object : Callback<ActorData> {
            override fun onFailure(call: Call<ActorData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<ActorData>, response: Response<ActorData>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorLoaded(response.body()!!)
                }
            }
        })
    }

    override fun createActor(data: ActorData, callback: IActorController.ICreateActorCallback) {
        App.api.createActor(data).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) callback.onActorCreated()
                else callback.onError()
            }
        })
    }

    override fun deleteActor(id: Int, callback: IActorController.IDeleteActorCalback) {
        App.api.deleteActor(id).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) callback.onActorDeleted()
                else callback.onError()
            }
        })
    }

    override fun updateActor(data: ActorData, callback: IActorController.IUpdateActorCallback) {
        App.api.updateActor(data).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) callback.onActorUpdated()
                else callback.onError()
            }
        })
    }

    override fun getActors(callback: IActorController.IGetActorsCallback) {
        App.api.getActors().enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsBySex(sex: String, callback: IActorController.IGetActorsBySexCallback) {
        App.api.getActorsBySex(sex).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsByExp(exp: Int, callback: IActorController.IGetActorsByExpCallback) {
        App.api.getActorsByExperience(exp).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsByBirth(birth: String, callback: IActorController.IGetActorsByBirthCallback) {
        App.api.getActorsByBirthDate(birth).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsByAge(age: Int, callback: IActorController.IGetActorsByAgeCallback) {
        App.api.getActorsByAge(age).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsByChildrenCallback(amount: Int, callback: IActorController.IGetActorsByChildrenCallback) {
        App.api.getActorsByChildrenAmount(amount).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorsBySalary(salary: Int, callback: IActorController.IGetActorsBySalaryCallback) {
        App.api.getActorsBySalary(salary).enqueue(object : Callback<List<ActorData>> {
            override fun onFailure(call: Call<List<ActorData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<ActorData>>, response: Response<List<ActorData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getRankedActors(callback: IActorController.IGetRankedActorsCallback) {
        App.api.getRankedActors().enqueue(object : Callback<Pair<List<ActorData>, Int>> {
            override fun onFailure(call: Call<Pair<List<ActorData>, Int>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Pair<List<ActorData>, Int>>, response: Response<Pair<List<ActorData>, Int>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getRankedActorsBySex(sex: String, callback: IActorController.IGetRankedActorsBySexCallback) {
        App.api.getRankedActorsBySex(sex).enqueue(object : Callback<Pair<List<ActorData>, Int>> {
            override fun onFailure(call: Call<Pair<List<ActorData>, Int>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Pair<List<ActorData>, Int>>, response: Response<Pair<List<ActorData>, Int>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getRankedActorsByAge(age: Int, callback: IActorController.IGetRankedActorsByAgeCallback) {
        App.api.getRankedActorsByAge(age).enqueue(object : Callback<Pair<List<ActorData>, Int>> {
            override fun onFailure(call: Call<Pair<List<ActorData>, Int>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Pair<List<ActorData>, Int>>, response: Response<Pair<List<ActorData>, Int>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getRankedActorsByContests(contests: List<String>, callback: IActorController.IGetRankedActorsByContestsCallback) {
        App.api.getRankedActorsByContests(contests).enqueue(object : Callback<Pair<List<ActorData>, Int>> {
            override fun onFailure(call: Call<Pair<List<ActorData>, Int>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<Pair<List<ActorData>, Int>>, response: Response<Pair<List<ActorData>, Int>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onActorsLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorRoles(id: Int, callback: IActorController.IGetActorRolesCallback) {
        App.api.getActorRoles(id).enqueue(object : Callback<List<RoleData>> {
            override fun onFailure(call: Call<List<RoleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<RoleData>>, response: Response<List<RoleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onRolesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorRolesByAge(id: Int, age: Int, callback: IActorController.IGetActorRolesByAgeCallback) {
        App.api.getActorRolesByAge(id, age).enqueue(object : Callback<List<RoleData>> {
            override fun onFailure(call: Call<List<RoleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<RoleData>>, response: Response<List<RoleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onRolesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorRolesByProducer(id: Int, producerFio: String, callback: IActorController.IGetActorRolesByProducerCallback) {
        App.api.getActorRolesByProducer(id, producerFio).enqueue(object : Callback<List<RoleData>> {
            override fun onFailure(call: Call<List<RoleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<RoleData>>, response: Response<List<RoleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onRolesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorRolesByPeriod(id: Int, from: String, to: String, callback: IActorController.IGetActorRolesByPeriodCallback) {
        App.api.getActorRolesByPeriod(id, from, to).enqueue(object : Callback<List<RoleData>> {
            override fun onFailure(call: Call<List<RoleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<RoleData>>, response: Response<List<RoleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onRolesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getActorRolesByGenre(id: Int, genre: String, callback: IActorController.IGetActorRolesByGenreCallback) {
        App.api.getActorRolesByGenre(id, genre).enqueue(object : Callback<List<RoleData>> {
            override fun onFailure(call: Call<List<RoleData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<RoleData>>, response: Response<List<RoleData>>) {
                if (!response.isSuccessful || response.body() == null){
                    callback.onError()
                } else {
                    callback.onRolesLoaded(response.body()!!)
                }
            }
        })
    }
}