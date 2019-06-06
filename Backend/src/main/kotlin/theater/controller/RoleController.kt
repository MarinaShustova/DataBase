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
@RequestMapping("/roles")
class RoleController(private val service: PerformanceService) {
    @PostMapping("/create")
    fun createRole(@RequestParam name: String): String {
        val toCreate = Role(-1, name)
        service.createRole(toCreate).toString()
        return "Role $name created"
    }

    @GetMapping
    fun getRoles(
        @RequestParam from: Int,
        @RequestParam size: Int
    ): ArrayList<RoleData> {
        return service.getRoles(Page(from, size))
    }

    @GetMapping("/{id}")
    fun getRoleById(
        @PathVariable id: Int
    ): RoleData {
        val role = service.findRole(id)
        return RoleData(role)
    }

    @PostMapping("/update/{id}")
    fun updateRole(@PathVariable id: Int, @RequestParam name: String): String {
        val toCreate = Role(id, name)
        val role = service.findRole(toCreate.id!!) ?: return "Role with id $id not found"
        service.updateRole(toCreate).toString()
        return "Role $id updated from ${role.name} to $name"
    }

    @PostMapping("/delete/{id}")
    fun deleteRole(@PathVariable id: Int): String {
        service.deleteRole(id).toString()
        return "Deleted role $id"
    }

    @PostMapping("/link/{id}")
    fun addFeatureToRole(@PathVariable id: Int, @RequestParam id2: Int): String {
        val featureId = id2
        val roleId = id
        val feature = service.findFeature(featureId) ?: return "Feature with id $id not found"
        val role = service.findRole(roleId) ?: return "Role with id $id2 not found"
        service.addFeatureToRole(roleId, featureId).toString()
        return "Role $roleId + Feature $featureId"
    }
}