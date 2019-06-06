package theater.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import theater.Db
import theater.TheaterDataSource
import theater.model.Actor
import theater.model.Employee
import theater.model.data.ActorData
import theater.model.data.RoleData
import theater.service.EmployeeService
import theater.service.GenreService
import java.lang.IllegalArgumentException
import java.sql.Date
import javax.sql.DataSource

@RestController
@RequestMapping("/actors")
class ActorController(private val service: EmployeeService) {

    @GetMapping("/{id}")
    fun getActorById(@PathVariable id: Int): ResponseEntity<ActorData> {
        val actor = service.getActorById(id)
        return if (null != actor) {
            ResponseEntity.ok(ActorData(actor))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/fio")
    fun getActorByName(@RequestParam(value = "fio", required = true) fio: String): ResponseEntity<ActorData> {
        val actor = service.getActorByName(fio)
        return if (null != actor) {
            ResponseEntity.ok(ActorData(actor))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/create")
    fun createActor(@RequestBody data: ActorData): ResponseEntity<String> {
        val relatedEmployee = Employee(-1, data.fio, data.sex, Date.valueOf(data.birthDate),
                data.childrenAmount, data.salary, data.origin,
                Date.valueOf(data.hireDate))
        val toCreate = Actor(0, relatedEmployee, data.isStudent)
        return ResponseEntity.ok("Created actor with id ${service.createActor(toCreate).toString()}")
    }

    @DeleteMapping("/{id}")
    fun deleteActor(@PathVariable id: Int): ResponseEntity<String> {
        val toDelete = service.getActorById(id)
        return if (toDelete != null) {
            service.deleteActor(id)
            ResponseEntity.ok("Deleted actor with id ${id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/update")
    fun updateActor(@RequestBody data: ActorData): ResponseEntity<String> {
        val toUpdate = service.getActorById(data.id)
        return if (toUpdate != null) {
            service.updateActor(toUpdate)
            ResponseEntity.ok("Updated actor with id ${toUpdate.id}")
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getActors(): ResponseEntity<List<ActorData>> {
        val actors = service.getActors()
        val eData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(eData)
    }

    @GetMapping("/sex")
    fun getActorsBySex(@RequestParam(value = "sex", required = true) sex: String): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsBySex(sex)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/exp")
    fun getActorsByExperience(@RequestParam(value = "exp", required = true) exp: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByExperience(exp)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/birth")
    fun getActorsByBirthDate(@RequestParam(value = "birth", required = true) birth: String): ResponseEntity<List<ActorData>> {
        return try {
            val date = Date.valueOf(birth)
            val actors = service.getActorsByBirthDate(date)
            val aData = actors.asSequence().map { ActorData(it) }.toList()
            return ResponseEntity.ok(aData)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/age")
    fun getActorsByAge(@RequestParam(value = "age") age: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByAge(age)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/children_amount")
    fun getActorsByChildrenAmount(@RequestParam(value = "children_amount") children_amount: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsByChildrenAmount(children_amount)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/salary")
    fun getActorsBySalary(@RequestParam(value = "salary") salary: Int): ResponseEntity<List<ActorData>> {
        val actors = service.getActorsBySalary(salary)
        val aData = actors.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(aData)
    }

    @GetMapping("/ranked")
    fun getRankedActors():  ResponseEntity<Pair<List<ActorData>, Int>> {
        val actors = service.getActorsWithRanks()
        val aData = actors.first.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(Pair(aData, actors.second))
    }

    @GetMapping("/ranked/sex")
    fun getRankedActorsBySex(@RequestParam(value = "sex") sex: String):  ResponseEntity<Pair<List<ActorData>, Int>> {
        val actors = service.getActorsWithRanksSex(sex)
        val aData = actors.first.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(Pair(aData, actors.second))
    }

    @GetMapping("/ranked/age")
    fun getRankedActorsByAge(@RequestParam(value = "age") age: Int): ResponseEntity<Pair<List<ActorData>, Int>> {
        val actors = service.getActorsWithRanksAge(age)
        val aData = actors.first.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(Pair(aData, actors.second))
    }

    @GetMapping("/ranked/contests")
    fun getRankedActorsByAge(@RequestBody contests: List<String>): ResponseEntity<Pair<List<ActorData>, Int>> {
        val actors = service.getActorsWithRanksContests(contests)
        val aData = actors.first.asSequence().map { ActorData(it) }.toList()
        return ResponseEntity.ok(Pair(aData, actors.second))
    }

    @GetMapping("/roles/{id}")
    fun getActorsRoles(@PathVariable(value = "id") id: Int): ResponseEntity<List<RoleData>> {
        val roles = service.getActorsRoles(id)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }

    @GetMapping("/roles/age/{id}")
    fun getActorsRolesByAge(@PathVariable(value = "id") id: Int, @RequestParam(value = "age") age: Int):
            ResponseEntity<List<RoleData>> {
        val roles = service.getActorsRolesByAgeCategory(id, age)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }

    @GetMapping("/roles/producer/{id}")
    fun getActorsRolesByProducer(@PathVariable(value = "id") id: Int, @RequestParam(value = "producer") producerFio: String):
            ResponseEntity<List<RoleData>> {
        val producer = service.getProducerByName(producerFio)
        return if (null != producer) {
            val roles = service.getActorsRolesByProducer(id, producer.id)
            val rData = roles.asSequence().map { RoleData(it) }.toList()
            ResponseEntity.ok(rData)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/roles/period/{id}")
    fun getActorsRolesByPeriod(@PathVariable(value = "id") id: Int,
                               @RequestParam(value = "startDate") startDate: String,
                               @RequestParam(value = "endDate") endDate: String): ResponseEntity<List<RoleData>> {
        val start = Date.valueOf(startDate)
        val end = Date.valueOf(endDate)
        val roles = service.getActorsRolesByPeriod(id, start, end)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }

    @GetMapping("/roles/genre/{id}")
    fun getActorsRolesByGenre(@PathVariable(value = "id") id: Int,
                              @RequestParam(value = "genre") genreName: String): ResponseEntity<List<RoleData>> {
        return try {
            val roles = service.getActorsRolesByGenre(id, genreName)
            val rData = roles.asSequence().map { RoleData(it) }.toList()
            ResponseEntity.ok(rData)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }

}