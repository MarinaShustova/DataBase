package theater.controller

import org.springframework.web.bind.annotation.*
import theater.dao.CountryDao
import theater.dao.EmployeesDao
import theater.dao.Page
import theater.dao.SpectacleDao
import theater.model.ConcertTour
import theater.model.Feature
import theater.model.Performance
import theater.model.Role
import theater.service.PerformanceService
import theater.service.Service
import java.sql.Date

@RestController
class PerformanceController(private val service: PerformanceService) {

    @RequestMapping("/performances")
    @PostMapping("/create")
    fun createPerformance(@RequestParam pr_des : String,
                          @RequestParam pr_dir : String, @RequestParam pr_cond : String,
                          @RequestParam season : Int): String {
        val toCreate = Performance(-1, pr_des, pr_dir, pr_cond, season)
        return service.createPerformance(toCreate).toString()
    }

    @GetMapping("/allPerformances")
    fun getPerformances(@RequestParam from : Int,
                        @RequestParam size : Int): String {
        return service.getPerformances(Page(from, size)).map { "${it.production_designer} ${it.production_director}" +
                " ${it.production_conductor} ${it.season}"}.joinToString("\n")
    }

    @PostMapping("/updatePerformance/{id}")
    fun updatePerformance(@PathVariable id: Int, @RequestParam pr_des : String,
                          @RequestParam pr_dir : String, @RequestParam pr_cond : String,
                          @RequestParam season : Int):String{
        val toCreate = Performance(id,
            pr_des, pr_dir, pr_cond, season)
        val performance = service.findPerformance(toCreate.id!!) ?: return "Performance with id $id not found"
        return service.updatePerformance(toCreate).toString()
    }

    @PostMapping("/deletePerformance/{id}")
    fun deletePerformance(@PathVariable id: Int): String {
        return service.deletePerformance(id).toString()
    }

    @PostMapping("/linkPerformanceWithRole/{id}")
    fun addRoleToPerformance(@PathVariable id: Int, @RequestParam id2 : Int): String{
        val performanceId = id
        val roleId = id2
        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
        return service.addRoleToPerformance(performanceId, roleId).toString()
    }

    @GetMapping("/spectacle/{id}")
    fun getPerformanceInfo(@PathVariable id: Int): String {
        return service.getPerformanceInfo(id).toString()
    }

//    @RequestMapping("/performances")
//    @PostMapping("/create")
//    fun createPerformances(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size % 4 != 0) {
//            return "not enough arguments"
//        }
//
//        val toCreate = ArrayList<Performance>()
//        for (i in 0 until args.size step 4) {
//            toCreate.add(Performance(-1, args[i], args[i + 1], args[i + 2], Integer.parseInt(args[i + 3])))
//
//        }
//        return service.createPerformances(toCreate).toString()
//    }

    @RequestMapping("/concert_tours")
    @PostMapping("/createTour")
    fun createConcertTour(@RequestParam city : String,
                          @RequestParam start : String, @RequestParam finish : String): String {
        val toCreate = ConcertTour(-1, city, Date.valueOf(start), Date.valueOf(finish))
        return service.createConcertTour(toCreate).toString()
    }

    @GetMapping("/allTours")
    fun getConcertTours(@RequestParam from : Int,
                        @RequestParam size : Int): String {
        return service.getConcertTours(Page(from, size)).map { "${it.city} ${it.start_date}" +
                " ${it.finish_date}"}.joinToString("\n")
    }

    @PostMapping("/updateTour/{id}")
    fun updateConcertTour(@PathVariable id: Int, @RequestParam city : String,
                          @RequestParam start : String, @RequestParam finish : String): String {
        val toCreate = ConcertTour(id, city, Date.valueOf(start), Date.valueOf(finish))
        val concertTour = service.findConcertTour(toCreate.id!!) ?: return "Concert tour with id $id not found"
        return service.updateConcertTour(toCreate).toString()
    }

    @PostMapping("/deleteTour/{id}")
    fun deleteConcertTour(@PathVariable id: Int): String {
        return service.deleteConcertTour(id).toString()
    }
    @PostMapping("/linkTourWithPerformance/{id}")
    fun addConcertTourToPerformance(@PathVariable id: Int, @RequestParam id2 : Int): String{
        val performanceId = id
        val concertTourId = id2
        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
        val concertTour = service.findConcertTour(concertTourId) ?: return "Concert tour with id $id2 not found"
        return service.addConcertTourToPerformance(performanceId, concertTourId).toString()
    }
    @GetMapping("/spectacleTour/{id}")
    fun getTourTroupe(@PathVariable id: Int,
                      @RequestParam start : String, @RequestParam finish : String): String {
        val startD = Date.valueOf(start)
        val finishD = Date.valueOf(finish)
        return service.getTourTroupe(id, startD, finishD)
    }

//    @RequestMapping("/concert_tours")
//    @PostMapping("/create")
//    fun createConcertTours(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size % 3 != 0) {
//            return "not enough arguments"
//        }
//
//        val toCreate = ArrayList<ConcertTour>()
//        for (i in 0 until args.size step 3) {
//            toCreate.add(ConcertTour(-1, args[i], Date.valueOf(args[i + 1]), Date.valueOf(args[i + 2])))
//
//        }
//        return service.createConcertTours(toCreate).toString()
//    }

    @RequestMapping("/roles")
    @PostMapping("/createRole")
    fun createRole(@RequestParam name : String): String {
        val toCreate = Role(-1, name)
        return service.createRole(toCreate).toString()
    }
    @GetMapping("/allRoles")
    fun getRoles(@RequestParam from : Int,
                 @RequestParam size : Int): String {
        return service.getRoles(Page(from, size)).map { "${it.name}" }.joinToString("\n")
    }
    @PostMapping("/updateRole/{id}")
    fun updateRole(@PathVariable id: Int, @RequestParam name : String): String {
        val toCreate = Role(id, name)
        val role = service.findRole(toCreate.id!!) ?: return "Role with id $id not found"
        return service.updateRole(toCreate).toString()
    }
    @PostMapping("/deleteRole/{id}")
    fun deleteRole(@PathVariable id: Int): String {
        return service.deleteRole(id).toString()
    }
    @PostMapping("/linkRoleWithFeature/{id}")
    fun addFeatureToRole(@PathVariable id: Int, @RequestParam id2 : Int): String{
        val featureId = id
        val roleId = id2
        val feature = service.findFeature(featureId) ?: return "Feature with id $id not found"
        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
        return service.addFeatureToRole(roleId, featureId).toString()
    }

//    @RequestMapping("/roles")
//    @PostMapping("/create")
//    fun createRoles(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty()) {
//            return "not enough arguments"
//        }
//
//        val toCreate = ArrayList<Role>()
//        for (i in 0 until args.size step 1) {
//            toCreate.add(Role(-1, args[i]))
//
//        }
//        return service.createRoles(toCreate).toString()
//    }

    @RequestMapping("/features")
    @PostMapping("/createFeature")
    fun createFeature(@RequestParam name : String, @RequestParam value : String): String {
        val toCreate = Feature(-1, name, value)
        return service.createFeature(toCreate).toString()
    }
//    @RequestMapping("/features")
    @GetMapping("/allFeatures")
    fun getFeatures(@RequestParam from : Int,
                    @RequestParam size : Int): String {
        return service.getFeatures(Page(from, size)).map { "${it.name} ${it.value}"}.joinToString("\n")
    }
//    @RequestMapping("/features")
    @PostMapping("/updateFeature/{id}")
    fun updateFeature(@PathVariable id: Int, @RequestParam name : String, @RequestParam value : String): String {
        val toCreate = Feature(id, name, value)
        val feature = service.findFeature(toCreate.id!!) ?: return "Feature with id $id not found"
        return service.updateFeature(toCreate).toString()
    }
//    @RequestMapping("/features")
    @PostMapping("/deleteFeature/{id}")
    fun deleteFeature(@PathVariable id: Int): String {
        return service.deleteFeature(id).toString()
    }


//    @RequestMapping("/features")
//    @PostMapping("/create")
//    fun createFeatures(argsStr: String): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size % 2 != 0) {
//            return "not enough arguments"
//        }
//
//        val toCreate = ArrayList<Feature>()
//        for (i in 0 until args.size step 2) {
//            toCreate.add(Feature(-1, args[i], args[i + 1]))
//
//        }
//        return service.createFeatures(toCreate).toString()
//    }

//    @RequestMapping("/performances")
//    @GetMapping
//    fun getPerformances(@RequestParam from : Int,
//                        @RequestParam size : Int): String {
//        return service.getPerformances(Page(from, size)).map { "${it.production_designer} ${it.production_director}" +
//                " ${it.production_conductor} ${it.season}"}.joinToString("\n")
//    }

//    @RequestMapping("/concert_tours")
//    @GetMapping
//    fun getConcertTours(@RequestParam from : Int,
//                        @RequestParam size : Int): String {
//        return service.getConcertTours(Page(from, size)).map { "${it.city} ${it.start_date}" +
//                " ${it.finish_date}"}.joinToString("\n")
//    }

//    @RequestMapping("/roles")
//    @GetMapping
//    fun getRoles(@RequestParam from : Int,
//                 @RequestParam size : Int): String {
//        return service.getRoles(Page(from, size)).map { "${it.name}" }.joinToString("\n")
//    }

//    @RequestMapping("/features")
//    @GetMapping
//    fun getFeatures(@RequestParam from : Int,
//                    @RequestParam size : Int): String {
//        return service.getFeatures(Page(from, size)).map { "${it.name} ${it.value}"}.joinToString("\n")
//    }

//    @RequestMapping("/performances")
//    @PostMapping("/update/{id}")
//    fun updatePerformance(@PathVariable id: Int, @RequestParam pr_des : String,
//                          @RequestParam pr_dir : String, @RequestParam pr_cond : String,
//                          @RequestParam season : Int):String{
//        val toCreate = Performance(id,
//            pr_des, pr_dir, pr_cond, season)
//        val performance = service.findPerformance(toCreate.id!!) ?: return "Performance with id $id not found"
//        return service.updatePerformance(toCreate).toString()
//    }

//    @RequestMapping("/concert_tours")
//    @PostMapping("/update/{id}")
//    fun updateConcertTour(@PathVariable id: Int, @RequestParam city : String,
//                          @RequestParam start : String, @RequestParam finish : String): String {
//        val toCreate = ConcertTour(id, city, Date.valueOf(start), Date.valueOf(finish))
//        val concertTour = service.findConcertTour(toCreate.id!!) ?: return "Concert tour with id $id not found"
//        return service.updateConcertTour(toCreate).toString()
//    }

//    @RequestMapping("/roles")
//    @PostMapping("/update/{id}")
//    fun updateRole(@PathVariable id: Int, @RequestParam name : String): String {
//        val toCreate = Role(id, name)
//        val role = service.findRole(toCreate.id!!) ?: return "Role with id $id not found"
//        return service.updateRole(toCreate).toString()
//    }

//    @RequestMapping("/features")
//    @PostMapping("/update/{id}")
//    fun updateFeature(@PathVariable id: Int, @RequestParam name : String, @RequestParam value : String): String {
//        val toCreate = Feature(id, name, value)
//        val feature = service.findFeature(toCreate.id!!) ?: return "Feature with id $id not found"
//        return service.updateFeature(toCreate).toString()
//    }

//    @RequestMapping("/performances")
//    @PostMapping("/delete/{id}")
//    fun deletePerformance(@PathVariable id: Int): String {
//        return service.deletePerformance(id).toString()
//    }

//    @RequestMapping("/concert_tours")
//    @PostMapping("/delete/{id}")
//    fun deleteConcertTour(@PathVariable id: Int): String {
//        return service.deleteConcertTour(id).toString()
//    }

//    @RequestMapping("/roles")
//    @PostMapping("/delete/{id}")
//    fun deleteRole(@PathVariable id: Int): String {
//        return service.deleteRole(id).toString()
//    }

//    @RequestMapping("/features")
//    @PostMapping("/delete/{id}")
//    fun deleteFeature(@PathVariable id: Int): String {
//        return service.deleteFeature(id).toString()
//    }

//    @RequestMapping("/concert_tours")
//    @PostMapping("/link/{id}")
//    fun addConcertTourToPerformance(@PathVariable id: Int, @RequestParam id2 : Int): String{
//        val performanceId = id
//        val concertTourId = id2
//        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
//        val concertTour = service.findConcertTour(concertTourId) ?: return "Concert tour with id $id2 not found"
//        return service.addConcertTourToPerformance(performanceId, concertTourId).toString()
//    }

//    @RequestMapping("/performances")
//    @PostMapping("/link/{id}")
//    fun addRoleToPerformance(@PathVariable id: Int, @RequestParam id2 : Int): String{
//        val performanceId = id
//        val roleId = id2
//        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
//        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
//        return service.addRoleToPerformance(performanceId, roleId).toString()
//    }

//    @RequestMapping("/roles")
//    @PostMapping("/link/{id}")
//    fun addFeatureToRole(@PathVariable id: Int, @RequestParam id2 : Int): String{
//        val featureId = id
//        val roleId = id2
//        val feature = service.findFeature(featureId) ?: return "Feature with id $id not found"
//        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
//        return service.addFeatureToRole(roleId, featureId).toString()
//    }

//    fun getTourTroupe(argsStr: String, spectacleDao: SpectacleDao): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 3) {
//            return "3 arg expected"
//        }
//        val spectacle =  spectacleDao.getSpectacleByName(args[0])!!.id
//        val start = Date.valueOf(args[1])
//        val finish = Date.valueOf(args[2])
//        return service.getTourTroupe(spectacle, start, finish)
//    }
//    @RequestMapping("/concert_tours")
//    @GetMapping("/spectacle/{id}")
//    fun getTourTroupe(@PathVariable id: Int,
//                      @RequestParam start : String, @RequestParam finish : String): String {
//        val startD = Date.valueOf(start)
//        val finishD = Date.valueOf(finish)
//        return service.getTourTroupe(id, startD, finishD)
//    }

//    fun getPerformanceInfo(argsStr: String, spectacleDao: SpectacleDao): String {
//        val args = argsStr.split(",")
//            .map { it.trim() }
//        if (argsStr.isEmpty() || args.size != 1) {
//            return "1 arg expected"
//        }
//        val spectacle =  spectacleDao.getSpectacleByName(args[0])!!.id
//        return service.getPerformanceInfo(spectacle).toString()
//    }
//    @RequestMapping("/performances")
//    @GetMapping("/spectacle/{id}")
//    fun getPerformanceInfo(@PathVariable id: Int): String {
//        return service.getPerformanceInfo(id).toString()
//    }
}