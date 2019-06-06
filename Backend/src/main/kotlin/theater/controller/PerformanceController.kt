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
import theater.exception.PerformanceNotFoundException

@RestController
@RequestMapping("/performances")
class PerformanceController(private val service: PerformanceService) {
    @PostMapping("/create")
    fun createPerformance(
        @RequestParam pr_des: Int,
        @RequestParam pr_dir: Int, @RequestParam pr_cond: Int,
        @RequestParam season: Int
    ): String {
        val toCreate = Performance(-1, pr_des, pr_dir, pr_cond, season)
        service.createPerformance(toCreate).toString()
        return "Performance created (${pr_des}, $pr_dir, $pr_cond, $season)"
    }

    @GetMapping
    fun getPerformances(
        @RequestParam num: Int,
        @RequestParam size: Int
    ): ArrayList<PerformanceData> {
        return service.getPerformances(Page(num, size))
    }

    @GetMapping("/{id}")
    fun getPerformanceById(
        @PathVariable id: Int
    ): PerformanceData {
        val p = service.findPerformance(id) ?: throw PerformanceNotFoundException()
        return PerformanceData(p)
    }

    @PostMapping("/update/{id}")
    fun updatePerformance(
        @PathVariable id: Int, @RequestParam pr_des: Int,
        @RequestParam pr_dir: Int, @RequestParam pr_cond: Int,
        @RequestParam season: Int
    ): String {
        val toCreate = Performance(
            id,
            pr_des, pr_dir, pr_cond, season
        )
        val performance = service.findPerformance(toCreate.id) ?: return "Performance with id $id not found"
        service.updatePerformance(toCreate).toString()
        return "Updated performance: from (${performance.production_designer}, " +
                "${performance.production_director}, ${performance.production_conductor}, ${performance.season})" +
                " to ($pr_des, $pr_dir, $pr_cond, $season)"
    }

    @PostMapping("/delete/{id}")
    fun deletePerformance(@PathVariable id: Int): String {
        service.deletePerformance(id).toString()
        return "Deleted performance with id$id"
    }

    @PostMapping("/link/{id}")
    fun addRoleToPerformance(@PathVariable id: Int, @RequestParam id2: Int): String {
        val performanceId = id
        val roleId = id2
        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
        service.addRoleToPerformance(performanceId, roleId).toString()
        return "Performance $id + Role $id2"
    }

    @GetMapping("/spectacle/{id}")
    fun getPerformanceInfo(@PathVariable id: Int): Info {
        return service.getPerformanceInfo(id)
    }
}