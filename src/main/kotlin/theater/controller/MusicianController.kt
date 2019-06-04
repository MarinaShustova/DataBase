package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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