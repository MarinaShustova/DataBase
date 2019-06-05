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

    @GetMapping
    fun getActorByName(@RequestParam(value = "fio", required = false) fio: String): ResponseEntity<ActorData> {
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
        return try {
            val date = Date.valueOf(birth)
            val actors = service.getActorsByBirthDate(date)
            val aData = actors.asSequence().map { ActorData(it) }.toList()
            return ResponseEntity.ok(aData)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
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

    @GetMapping("/{id}/roles")
    fun getActorsRoles(@PathVariable(value = "id") id: Int): ResponseEntity<List<RoleData>> {
        val roles = service.getActorsRoles(id)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }


    @GetMapping("/{id}/roles")
    fun getActorsRolesByAge(@PathVariable(value = "id") id: Int, @RequestParam(value = "age") age: Int):
            ResponseEntity<List<RoleData>> {
        val roles = service.getActorsRolesByAgeCategory(id, age)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }

    @GetMapping("/{id}/roles")
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

    @GetMapping("/{id}/roles")
    fun getActorsRolesByPeriod(@PathVariable(value = "id") id: Int,
                               @RequestParam(value = "startDate") startDate: String,
                               @RequestParam(value = "endDate") endDate: String): ResponseEntity<List<RoleData>> {
        val start = Date.valueOf(startDate)
        val end = Date.valueOf(endDate)
        val roles = service.getActorsRolesByPeriod(id, start, end)
        val rData = roles.asSequence().map { RoleData(it) }.toList()
        return ResponseEntity.ok(rData)
    }

    @GetMapping("/{id}/roles")
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