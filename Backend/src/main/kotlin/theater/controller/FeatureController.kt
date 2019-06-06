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
import theater.exception.FeatureNotFoundException

@RestController
@RequestMapping("/features")
class FeatureController(private val service: PerformanceService) {

    @PostMapping("/create")
    fun createFeature(@RequestParam name: String, @RequestParam value: String): String {
        val toCreate = Feature(-1, name, value)
        service.createFeature(toCreate).toString()
        return "Created feature : ${name}, ${value}"
    }

    @GetMapping
    fun getFeatures(
        @RequestParam from: Int,
        @RequestParam size: Int
    ): ArrayList<FeatureData> {
        return service.getFeatures(Page(from, size))
    }

    @GetMapping("/{id}")
    fun getFeatureById(
        @PathVariable id: Int
    ): FeatureData {
        val feature = service.findFeature(id) ?: throw FeatureNotFoundException()
        return FeatureData(feature)
    }

    @PostMapping("/update/{id}")
    fun updateFeature(@PathVariable id: Int, @RequestParam name: String, @RequestParam value: String): String {
        val toCreate = Feature(id, name, value)
        val feature = service.findFeature(toCreate.id!!) ?: return "Feature with id $id not found"
        service.updateFeature(toCreate).toString()
        return "Update feature: from ${feature.name}, ${feature.value} to ${name}, ${value}"
    }

    @PostMapping("/delete/{id}")
    fun deleteFeature(@PathVariable id: Int): String {
        service.deleteFeature(id).toString()
        return "Delete feature: with id${id}"
    }
}