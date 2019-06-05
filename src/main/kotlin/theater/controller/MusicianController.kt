package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.model.Employee
import theater.model.Musician
import theater.model.data.MusicianData
import theater.service.EmployeeService
import java.lang.IllegalArgumentException
import java.sql.Date

@RestController
@RequestMapping("/musician")
class MusicianController(private val service: EmployeeService) {
    @GetMapping("/{id}")
    fun getMusicianById(@PathVariable id: Int): ResponseEntity<MusicianData> {
        val musician = service.getMusicianById(id)
        return if (null != musician) {
            return ResponseEntity.ok(MusicianData(musician))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/create")
    fun createMusician(@RequestBody data: MusicianData): ResponseEntity<String> {
        val relatedEmployee = Employee(-1, data.fio, data.sex, Date.valueOf(data.birthDate),
                data.childrenAmount, data.salary, data.origin,
                Date.valueOf(data.hireDate))
        val toCreate = Musician(0, relatedEmployee, data.instrument)
        return ResponseEntity.ok("Created musician with id ${service.createMusician(toCreate).toString()}")
    }

    @DeleteMapping("/{id}")
    fun deleteMusician(@PathVariable id: Int): ResponseEntity<String> {
        val toDelete = service.getActorById(id)
        return if (toDelete != null) {
            service.deleteMusician(id)
            return ResponseEntity.ok("Deleted musician with id ${id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/update")
    fun updateMusician(@RequestBody data: MusicianData): ResponseEntity<String> {
        val toUpdate = service.getMusicianById(data.id)
        return if (toUpdate != null) {
            service.updateMusician(toUpdate)
            return ResponseEntity.ok("Updated musician with id ${toUpdate.id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping
    fun getMusicianByName(@RequestParam(value = "fio") fio: String): ResponseEntity<MusicianData> {
        val musician = service.getMusicianByName(fio)
        return if (null != musician) {
            return ResponseEntity.ok(MusicianData(musician))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getMusicians(): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusicians()
        val eData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping
    fun getMusiciansBySex(@RequestParam(value = "sex") sex: String): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusiciansBySex(sex)
        val mData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(mData)
    }


    @GetMapping
    fun getMusiciansByExperience(@RequestParam(value = "exp") exp: Int): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusiciansByExperience(exp)
        val mData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(mData)
    }

    @GetMapping
    fun getMusiciansByBirthDate(@RequestParam(value = "birth") birth: String): ResponseEntity<List<MusicianData>> {
        try {
            val date = Date.valueOf(birth)
            val musicians = service.getMusiciansByBirthDate(date)
            val mData = musicians.asSequence().map { MusicianData(it) }.toList()
            return ResponseEntity.ok(mData)
        } catch (e: IllegalArgumentException) {
        }
        return ResponseEntity.badRequest().build()
    }

    @GetMapping
    fun getMusiciansByAge(@RequestParam(value = "age") age: Int): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusiciansByAge(age)
        val mData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(mData)
    }

    @GetMapping
    fun getMusiciansByChildrenAmount(@RequestParam(value = "children_amount") children_amount: Int): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusiciansByChildrenAmount(children_amount)
        val mData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(mData)
    }

    @GetMapping
    fun getMusiciansBySalary(@RequestParam(value = "salary") salary: Int): ResponseEntity<List<MusicianData>> {
        val musicians = service.getMusiciansBySalary(salary)
        val mData = musicians.asSequence().map { MusicianData(it) }.toList()
        return ResponseEntity.ok(mData)
    }
}