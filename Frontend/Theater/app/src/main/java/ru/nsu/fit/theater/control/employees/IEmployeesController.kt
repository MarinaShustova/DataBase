package ru.nsu.fit.theater.control.employees

import ru.nsu.fit.theater.control.ICallback
import ru.nsu.fit.theater.control.IController
import ru.nsu.fit.theater.retrofit.model.EmployeeData

interface IEmployeesController: IController {
    interface IGetEmployeeByIdCallback: ICallback {
        fun onEmployeeLoaded(employeeData: EmployeeData)
    }

    interface IGetEmployeeByNameCallback: ICallback {
        fun onEmployeeLoaded(employeeData: EmployeeData)
    }

    interface IGetEmployeesCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)
    }

    interface IGetEmployeesBySexCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)

    }

    interface IGetEmployeesByExpCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)

    }

    interface IGetEmployeesByBirthCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)

    }

    interface IGetEmployeesByChildrenCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)

    }

    interface IGetEmployeesByAgeCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)
    }

    interface IGetEmployeesBySalaryCallback: ICallback {
        fun onEmployeesLoaded(employees: List<EmployeeData>)
    }

    fun getEmployeeById(id: Int, callback: IGetEmployeeByIdCallback)

    fun getEmployeeByName(fio: String, callback: IGetEmployeeByNameCallback)

    fun getEmployees(callback: IGetEmployeesCallback)

    fun getEmployeesBySex(sex: String, callback: IGetEmployeesBySexCallback)

    fun getEmployeesByExp(exp: Int, callback: IGetEmployeesByExpCallback)

    fun getEmployeesByBirth(birth: String, callback: IGetEmployeesByBirthCallback)

    fun getEmployeesByAge(age: Int, callback: IGetEmployeesByAgeCallback)

    fun getEmployeesByChildren(amount: Int, callback: IGetEmployeesByChildrenCallback)

    fun getEmployeesBySalary(salary: Int, callback: IGetEmployeesBySalaryCallback)
}