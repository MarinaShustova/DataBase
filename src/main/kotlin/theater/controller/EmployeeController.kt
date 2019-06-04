package theater.controller

import org.springframework.web.bind.annotation.RestController
import theater.dao.CountryDao
import theater.dao.SpectacleDao
import theater.service.EmployeeService
import theater.model.*
import java.sql.Date

@RestController
class EmployeeController(private val service: EmployeeService) {

    fun createProducer(argsStr: String): String {
        // fio sex birth child salary origin hire activity
        val args = argsStr.split(", ").map { it.trim() }
        if (argsStr.isEmpty() || args.size != 8) {
            return "8 args expected"
        }
        val relatedEmployee = Employee(-1, args[0], args[1],  Date.valueOf(args[2]),
                args[3].toInt(), args[4].toInt(), args[5],
                Date.valueOf(args[6]))
        val toCreate = Producer(null, relatedEmployee, args[7])
        return service.createProducer(toCreate).toString()
    }

    fun deleteProducer(argsStr: String): String {
        val args = argsStr.trim()
        if (argsStr.isEmpty()) {
            return "1 arg expected"
        }
        val toDeleteId = args[0].toLong()
        service.deleteProducer(toDeleteId)
        return "deleted " + args[0]
    }

    fun deleteActor(argsStr: String): String {
        val args = argsStr.trim()
        if (argsStr.isEmpty()) {
            return "1 arg expected"
        }
        val toDeleteId = args[0].toLong()
        service.deleteActor(toDeleteId)
        return "deleted " + args[0]
    }

    fun deleteMusician(argsStr: String): String {
        val args = argsStr.trim()
        if (argsStr.isEmpty()) {
            return "1 arg expected"
        }
        val toDeleteId = args[0].toLong()
        service.deleteMusician(toDeleteId)
        return "deleted " + args[0]
    }

    fun deleteServant(argsStr: String): String {
        val args = argsStr.trim()
        if (argsStr.isEmpty()) {
            return "1 arg expected"
        }
        val toDeleteId = args[0].toLong()
        service.deleteServant(toDeleteId)
        return "deleted " + args[0]
    }

    fun createActor(argsStr: String): String {
        // fio sex birth child salary origin hire is_student
        val args = argsStr.split(", ").map { it.trim() }
        if (argsStr.isEmpty() || args.size != 8 || args.size != 7) {
            return "7-8 args expected"
        }
        val relatedEmployee = Employee(-1, args[0], args[1],  Date.valueOf(args[2]),
                Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5],
                Date.valueOf(args[6]))
        val toCreate = Actor(null, relatedEmployee, (args.size == 7))
        return service.createActor(toCreate).toString()
    }

    fun createMusician(argsStr: String): String {
        // fio sex birth child salary origin hire instrument
        val args = argsStr.split(", ").map { it.trim() }
        if (argsStr.isEmpty() || args.size != 8 || args.size != 7) {
            return "8 args expected"
        }
        val relatedEmployee = Employee(-1, args[0], args[1],  Date.valueOf(args[2]),
                args[3].toInt(), args[4].toInt(), args[5],
                Date.valueOf(args[6]))
        val toCreate = Musician(null, relatedEmployee, args[7])
        return service.createMusician(toCreate).toString()
    }

    fun createServant(argsStr: String): String {
        val args = argsStr.split(", ").map { it.trim() }
        if (argsStr.isEmpty() || args.size != 8 || args.size != 7) {
            return "8 args expected"
        }
        val relatedEmployee = Employee(-1, args[0], args[1],  Date.valueOf(args[2]),
                args[3].toInt(), args[4].toInt(), args[5],
                Date.valueOf(args[6]))
        val toCreate = Servant(null, relatedEmployee, args[7])
        return service.createServant(toCreate).toString()
    }

    private fun parsePropertyArgs(args: List<String>): Map<String, String> {
        return args
                .asSequence()
                .associate {
                    Pair(it.split(":")[0], it.split(":")[1])
                }
    }

    fun updateProducer(argsStr: String): String { //arg format: <id> property_name:new_value property_name:new_value
        val args = argsStr.split(", ").map { it.trim() }
        val id = args[0].toLong()
        val propertyMap = parsePropertyArgs(args.subList(1, args.size))
        service.updateProducer(id, propertyMap)
        return "updated"
    }

    fun updateActor(argsStr: String): String { //arg format: <id> property_name:new_value property_name:new_value
        val args = argsStr.split(", ").map { it.trim() }
        val id = args[0].toLong()
        val propertyMap = parsePropertyArgs(args.subList(1, args.size))
        service.updateActor(id, propertyMap)
        return "updated"
    }

    fun updateMusician(argsStr: String): String { //arg format: <id> property_name:new_value property_name:new_value
        val args = argsStr.split(", ").map { it.trim() }
        val id = args[0].toLong()
        val propertyMap = parsePropertyArgs(args.subList(1, args.size))
        service.updateMusician(id, propertyMap)
        return "updated"
    }

    fun updateServant(argsStr: String): String { //arg format: <id> property_name:new_value property_name:new_value
        val args = argsStr.split(", ").map { it.trim() }
        val id = args[0].toLong()
        val propertyMap = parsePropertyArgs(args.subList(1, args.size))
        service.updateServant(id, propertyMap)
        return "updated"
    }

    //functions for selections

    fun getActorWithRank():String {
        return service.getActorsWithRanks()
    }

    fun getActorWithRankSex(argsStr: String): String {
        return service.getActorsWithRanksSex(argsStr)
    }

    fun getActorWithRankAge(argsStr: String, flag : Int): String {
        val age = argsStr.toInt().times(flag)
        return  service.getActorsWithRanksAge(age)
    }

    fun getActorWithRankContests(argsStr: String): String {
        val contests = argsStr.split(",").map { it.trim() }.toList()
        return  service.getActorsWithRanksContests(contests)
    }

    fun getTourTroupe(argsStr: String, spectacleDao: SpectacleDao): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected"
        }
        val spectacle =  spectacleDao.getSpectacleByName(args[0])!!.id
        val start = Date.valueOf(args[1])
        val finish = Date.valueOf(args[2])
        return service.getTourTroupe(spectacle, start, finish)
    }

    fun getPerformanceInfo(argsStr: String, spectacleDao: SpectacleDao, countryDao: CountryDao): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        val spectacle =  spectacleDao.getSpectacleByName(args[0])!!.id
        return service.getPerformanceInfo(spectacle, countryDao).toString()
    }
}