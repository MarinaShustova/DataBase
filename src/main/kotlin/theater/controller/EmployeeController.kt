package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.service.EmployeeService
import theater.model.data.EmployeeData
import java.lang.IllegalArgumentException
import java.sql.Date


@RestController
@RequestMapping("/employees")
class EmployeeController(private val service: EmployeeService) {

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Int): ResponseEntity<EmployeeData> {
        val employee = service.getEmployeeById(id)
        return if (null != employee) {
            return ResponseEntity.ok(EmployeeData(employee))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getEmployeeByName(@RequestParam(value = "fio") fio: String): ResponseEntity<EmployeeData> {
        val employee = service.getEmployeeByName(fio)
        return if (null != employee) {
            return ResponseEntity.ok(EmployeeData(employee))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getEmployees(): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployees()
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping
    fun getEmployeesBySex(@RequestParam(value = "sex") sex: String): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployeesBySex(sex)
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }


    @GetMapping
    fun getEmployeesByExperience(@RequestParam(value = "exp") exp: Int): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployeesByExperience(exp)
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping
    fun getEmployeesByBirthDate(@RequestParam(value = "birth") birth: String): ResponseEntity<List<EmployeeData>> {
        try {
            val date = Date.valueOf(birth)
            val employees = service.getEmployeesByBirthDate(date)
            val eData = employees.asSequence().map { EmployeeData(it) }.toList()
            return ResponseEntity.ok(eData)
        } catch (e: IllegalArgumentException) {
        }
        return ResponseEntity.badRequest().build()
    }

    @GetMapping
    fun getEmployeesByAge(@RequestParam(value = "age") age: Int): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployeesByAge(age)
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping
    fun getEmployeesByChildrenAmount(@RequestParam(value = "children_amount") children_amount: Int): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployeesByChildrenAmount(children_amount)
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping
    fun getEmployeesBySalary(@RequestParam(value = "salary") salary: Int): ResponseEntity<List<EmployeeData>> {
        val employees = service.getEmployeesBySalary(salary)
        val eData = employees.asSequence().map { EmployeeData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

}