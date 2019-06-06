package ru.nsu.fit.theater.control.employees

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.nsu.fit.theater.App
import ru.nsu.fit.theater.retrofit.model.EmployeeData

class RetrofitEmployeesController: IEmployeesController {
    override fun getEmployeeById(id: Int, callback: IEmployeesController.IGetEmployeeByIdCallback) {
        App.api.getEmployeeById(id).enqueue(object : Callback<EmployeeData> {
            override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<EmployeeData>, response: Response<EmployeeData>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeeLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeeByName(fio: String, callback: IEmployeesController.IGetEmployeeByNameCallback) {
        App.api.getEmployeeByName(fio).enqueue(object : Callback<EmployeeData> {
            override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<EmployeeData>, response: Response<EmployeeData>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeeLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployees(callback: IEmployeesController.IGetEmployeesCallback) {
        App.api.getEmployees().enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesBySex(sex: String, callback: IEmployeesController.IGetEmployeesBySexCallback) {
        App.api.getEmployeesBySex(sex).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesByExp(exp: Int, callback: IEmployeesController.IGetEmployeesByExpCallback) {
        App.api.getEmployeesByExp(exp).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesByBirth(birth: String, callback: IEmployeesController.IGetEmployeesByBirthCallback) {
        App.api.getEmployeesByBirth(birth).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesByAge(age: Int, callback: IEmployeesController.IGetEmployeesByAgeCallback) {
        App.api.getEmployeesByAge(age).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesByChildren(amount: Int, callback: IEmployeesController.IGetEmployeesByChildrenCallback) {
        App.api.getEmployeesByChildrenAmount(amount).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }

    override fun getEmployeesBySalary(salary: Int, callback: IEmployeesController.IGetEmployeesBySalaryCallback) {
        App.api.getEmployeesBySalary(salary).enqueue(object : Callback<List<EmployeeData>> {
            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<List<EmployeeData>>, response: Response<List<EmployeeData>>) {
                if (!response.isSuccessful || response.body() == null) {
                    callback.onError()
                } else {
                    callback.onEmployeesLoaded(response.body()!!)
                }
            }
        })
    }
}