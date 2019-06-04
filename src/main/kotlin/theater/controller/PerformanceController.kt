package theater.controller

import org.springframework.web.bind.annotation.RestController
import theater.dao.Page
import theater.model.ConcertTour
import theater.model.Feature
import theater.model.Performance
import theater.model.Role
import theater.service.PerformanceService
import theater.service.Service
import java.sql.Date

@RestController
class PerformanceController(private val service: PerformanceService) {

    fun createPerformance(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 4) {
            return "4 arg expected"
        }

        //val group = service.getGroupByNumber(args[1]) ?: return "Group with number ${args[1]} not found"
        val toCreate = Performance(null, args[0], args[1], args[2], Integer.parseInt(args[3]))
        return service.createPerformance(toCreate).toString()
    }

    fun createPerformances(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size % 4 != 0) {
            return "not enough arguments"
        }

        val toCreate = ArrayList<Performance>()
        for (i in 0 until args.size step 4) {
            //val group = service.getGroupByNumber(args[i + 1]) ?: return "Group with number ${args[i + 1]} not found"
            toCreate.add(Performance(null, args[i], args[i + 1], args[i + 2], Integer.parseInt(args[i + 3])))

        }
        return service.createPerformances(toCreate).toString()
    }

    fun createConcertTour(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected"
        }
        val toCreate = ConcertTour(null, args[0], Date.valueOf(args[1]), Date.valueOf(args[2]))
        return service.createConcertTour(toCreate).toString()
    }

    fun createConcertTours(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size % 3 != 0) {
            return "not enough arguments"
        }

        val toCreate = ArrayList<ConcertTour>()
        for (i in 0 until args.size step 3) {
            toCreate.add(ConcertTour(null, args[i], Date.valueOf(args[i + 1]), Date.valueOf(args[i + 2])))

        }
        return service.createConcertTours(toCreate).toString()
    }

    fun createRole(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        val toCreate = Role(null, args[0])
        return service.createRole(toCreate).toString()
    }

    fun createRoles(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty()) {
            return "not enough arguments"
        }

        val toCreate = ArrayList<Role>()
        for (i in 0 until args.size step 1) {
            toCreate.add(Role(null, args[i]))

        }
        return service.createRoles(toCreate).toString()
    }

    fun createFeature(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }
        val toCreate = Feature(null, args[0], args[1])
        return service.createFeature(toCreate).toString()
    }

    fun createFeatures(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size % 2 != 0) {
            return "not enough arguments"
        }

        val toCreate = ArrayList<Feature>()
        for (i in 0 until args.size step 2) {
            toCreate.add(Feature(null, args[i], args[i + 1]))

        }
        return service.createFeatures(toCreate).toString()
    }

    fun getPerformances(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        val from = args[0].toInt()
        val size = args[1].toInt()
        return service.getPerformances(Page(from, size)).map { "${it.production_designer} ${it.production_director}" +
                " ${it.production_conductor} ${it.season}"}.joinToString("\n")
    }

    fun getConcertTours(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        val from = args[0].toInt()
        val size = args[1].toInt()
        return service.getConcertTours(Page(from, size)).map { "${it.city} ${it.start_date}" +
                " ${it.finish_date}"}.joinToString("\n")
    }

    fun getRoles(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        val from = args[0].toInt()
        val size = args[1].toInt()
        return service.getRoles(Page(from, size)).map { "${it.name}" }.joinToString("\n")
    }

    fun getFeatures(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        val from = args[0].toInt()
        val size = args[1].toInt()
        return service.getFeatures(Page(from, size)).map { "${it.name} ${it.value}"}.joinToString("\n")
    }

    fun updatePerformance(argsStr: String):String{
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 5) {
            return "5 arg expected"
        }
        val toCreate = Performance(Integer.parseInt(args[0]).toLong(),
            args[1], args[2], args[3], Integer.parseInt(args[4]))
        val performance = service.findPerformance(toCreate.id!!) ?: return "Performance with id ${args[0]} not found"
        return service.updatePerformance(toCreate).toString()
    }

    fun updateConcertTour(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 4) {
            return "4 arg expected"
        }
        val toCreate = ConcertTour(Integer.parseInt(args[0]).toLong(), args[1], Date.valueOf(args[2]), Date.valueOf(args[3]))
        val concertTour = service.findConcertTour(toCreate.id!!) ?: return "Concert tour with id ${args[0]} not found"
        return service.updateConcertTour(toCreate).toString()
    }

    fun updateRole(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }
        val toCreate = Role(Integer.parseInt(args[0]).toLong(), args[1])
        val role = service.findRole(toCreate.id!!) ?: return "Role with id ${args[0]} not found"
        return service.updateRole(toCreate).toString()
    }

    fun updateFeature(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected"
        }
        val toCreate = Feature(Integer.parseInt(args[0]).toLong(), args[1], args[2])
        val feature = service.findFeature(toCreate.id!!) ?: return "Feature with id ${args[0]} not found"
        return service.updateFeature(toCreate).toString()
    }

    fun deletePerformance(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return service.deletePerformance(Integer.parseInt(args[0]).toLong()).toString()
    }

    fun deleteConcertTour(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return service.deleteConcertTour(Integer.parseInt(args[0]).toLong()).toString()
    }

    fun deleteRole(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return service.deleteRole(Integer.parseInt(args[0]).toLong()).toString()
    }

    fun deleteFeature(argsStr: String): String {
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 1) {
            return "1 arg expected"
        }
        return service.deleteFeature(Integer.parseInt(args[0]).toLong()).toString()
    }

    fun addConcertTourToPerformance(argsStr: String): String{
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }
        val performanceId = Integer.parseInt(args[0]).toLong()
        val concertTourId = Integer.parseInt(args[1]).toLong();
        val performance = service.findPerformance(performanceId) ?: return "Performance with id ${args[0]} not found"
        val concertTour = service.findConcertTour(concertTourId) ?: return "Concert tour with id ${args[1]} not found"
        return service.addConcertTourToPerformance(performanceId, concertTourId).toString()
    }

    fun addRoleToPerformance(argsStr: String): String{
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }
        val performanceId = Integer.parseInt(args[0]).toLong()
        val roleId = Integer.parseInt(args[1]).toLong();
        val performance = service.findPerformance(performanceId) ?: return "Performance with id ${args[0]} not found"
        val role = service.findRole(roleId) ?: return "Role with id ${args[1]} not found"
        return service.addRoleToPerformance(performanceId, roleId).toString()
    }

    fun addFeatureToRole(argsStr: String): String{
        val args = argsStr.split(",")
            .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }
        val featureId = Integer.parseInt(args[1]).toLong()
        val roleId = Integer.parseInt(args[0]).toLong();
        val feature = service.findFeature(featureId) ?: return "Feature with id ${args[1]} not found"
        val role = service.findRole(roleId) ?: return "Role with id ${args[0]} not found"
        return service.addFeatureToRole(roleId, featureId).toString()
    }

    //    fun moveStudent(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 2) {
//            return "2 arg expected"
//        }
//
//        val id = args[0].toLong()
//
//        val toMove = service.findStudent(id) ?: return "Performance with $id does not exist"
//        val targetGroup = service.getGroupByNumber(args[1]) ?: return "Group with number ${args[1]} does not exist"
//        service.move(toMove, targetGroup)
//        return "Moved"
//    }
//    fun createGroup(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 1) {
//            return "1 arg expected"
//        }
//
//        val toCreate = ActualGroup(null, args[0])
//        return service.createGroup(toCreate).toString()
//    }

}