package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.model.data.ActorData
import theater.service.EmployeeService
import java.lang.IllegalArgumentException
import java.sql.Date

@RestController
@RequestMapping("/actors")
class ActorController(private val service: EmployeeService) {

    @GetMapping("/{id}")
    fun getActorById(@PathVariable id: Int): ResponseEntity<ActorData> {
        val actor = service.getActorById(id)
        return if (null != actor) {
            return ResponseEntity.ok(ActorData(actor))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getActorByName(@RequestParam(value = "fio", required = false) fio: String): ResponseEntity<ActorData> {
        val actor = service.getActorByName(fio)
        return if (null != actor) {
            return ResponseEntity.ok(ActorData(actor))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getActorsBySex(@RequestParam(value = "sex", required = false) sex: String): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsBySex(sex)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }


    @GetMapping
    fun getActorsByExperience(@RequestParam(value = "exp", required = false) exp: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByExperience(exp)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping
    fun getActorsByBirthDate(@RequestParam(value = "birth", required = false) birth: String): ResponseEntity<List<ActorData>> {
        try {
            val date = Date.valueOf(birth)
            val actors = service.getActorsByBirthDate(date)
            val aData = actors.asSequence().map { ActorData(it) }.toList()
            return ResponseEntity.ok(aData)
        } catch (e: IllegalArgumentException) {
        }
        return ResponseEntity.badRequest().build()
    }

    @GetMapping
    fun getActorsByAge(@RequestParam(value = "age", required = false) age: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByAge(age)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping
    fun getActorsByChildrenAmount(@RequestParam(value = "children_amount", required = false) children_amount: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByChildrenAmount(children_amount)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping
    fun getActorsBySalary(@RequestParam(value = "salary", required = false) salary: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsBySalary(salary)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/ranked")
    fun getRankedActors(): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsWithRanks()
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/ranked")
    fun getRankedActorsBySex(@RequestParam(value = "sex", required = false) sex: String): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsWithRanksSex(sex)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/ranked")
    fun getRankedActorsByAge(@RequestParam(value = "age", required = false) age: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsWithRanksAge(age)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/ranked/contests")
    fun getRankedActorsByAge(@RequestBody contests: List<String>): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsWithRanksContests(contests)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

}