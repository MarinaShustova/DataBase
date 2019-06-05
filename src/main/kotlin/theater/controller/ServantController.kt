package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.model.Employee
import theater.model.Producer
import theater.model.Servant
import theater.model.data.ProducerData
import theater.model.data.ServantData
import theater.service.EmployeeService
import java.sql.Date

@RestController
@RequestMapping("/servants")
class ServantController(private val service: EmployeeService) {
    @GetMapping("/{id}")
    fun getServantById(@PathVariable id: Int): ResponseEntity<ServantData> {
        val servant = service.getServantById(id)
        return if (null != servant) {
            ResponseEntity.ok(ServantData(servant))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getServantByName(@RequestParam(value = "fio", required = false) fio: String): ResponseEntity<ServantData> {
        val servant = service.getServantByName(fio)
        return if (null != servant) {
            ResponseEntity.ok(ServantData(servant))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getServants(): ResponseEntity<List<ServantData>> {
        val servants = service.getServants()
        val eData = servants.asSequence().map { ServantData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @PostMapping("/create")
    fun createServant(@RequestBody data: ServantData): ResponseEntity<String> {
        val relatedEmployee = Employee(-1, data.fio, data.sex, Date.valueOf(data.birthDate),
                data.childrenAmount, data.salary, data.origin,
                Date.valueOf(data.hireDate))
        val toCreate = Servant(0, relatedEmployee, data.activity)
        return ResponseEntity.ok("Created servant with id ${service.createServant(toCreate).toString()}")
    }

    @DeleteMapping("/{id}")
    fun deleteServant(@PathVariable id: Int): ResponseEntity<String> {
        val toDelete = service.getActorById(id)
        return if (toDelete != null) {
            service.deleteServant(id)
            ResponseEntity.ok("Deleted servant with id ${id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/update")
    fun updateServant(@RequestBody data: ServantData): ResponseEntity<String> {
        val toUpdate = service.getServantById(data.id)
        return if (toUpdate != null) {
            service.updateServant(toUpdate)
            ResponseEntity.ok("Updated servant with id ${toUpdate.id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }
}