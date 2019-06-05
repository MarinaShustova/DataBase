package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.model.Employee
import theater.model.Producer
import theater.model.data.ProducerData
import theater.service.EmployeeService
import java.sql.Date

@RestController
@RequestMapping("/producers")
class ProducerController(private val service: EmployeeService) {

    @GetMapping("/{id}")
    fun getProducerById(@PathVariable id: Int): ResponseEntity<ProducerData> {
        val producer = service.getProducerById(id)
        return if (null != producer) {
            ResponseEntity.ok(ProducerData(producer))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/fio")
    fun getProducerByName(@RequestParam(value = "fio") fio: String): ResponseEntity<ProducerData> {
        val producer = service.getProducerByName(fio)
        return if (null != producer) {
            ResponseEntity.ok(ProducerData(producer))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getProducers(): ResponseEntity<List<ProducerData>> {
        val producers = service.getProducers()
        val eData = producers.asSequence().map { ProducerData(it) }.toList()
        return ResponseEntity.ok(eData)
    }
    
    @PostMapping("/create")
    fun createProducer(@RequestBody data: ProducerData): ResponseEntity<String> {
        val relatedEmployee = Employee(-1, data.fio, data.sex, Date.valueOf(data.birthDate),
                data.childrenAmount, data.salary, data.origin,
                Date.valueOf(data.hireDate))
        val toCreate = Producer(0, relatedEmployee, data.activity)
        return ResponseEntity.ok("Created producer with id ${service.createProducer(toCreate).toString()}")
    }

    @DeleteMapping("/{id}")
    fun deleteProducer(@PathVariable id: Int): ResponseEntity<String> {
        val toDelete = service.getActorById(id)
        return if (toDelete != null) {
            service.deleteProducer(id)
            ResponseEntity.ok("Deleted producer with id ${id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/update")
    fun updateProducer(@RequestBody data: ProducerData): ResponseEntity<String> {
        val toUpdate = service.getProducerById(data.id)
        return if (toUpdate != null) {
            service.updateProducer(toUpdate)
            ResponseEntity.ok("Updated producer with id ${toUpdate.id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

}