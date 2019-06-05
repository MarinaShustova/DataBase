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
@RequestMapping("/tours")
class TourController(private val service: PerformanceService) {
    @PostMapping("/create")
    fun createConcertTour(
        @RequestParam city: String,
        @RequestParam start: String, @RequestParam finish: String
    ): String {
        val toCreate = ConcertTour(-1, city, Date.valueOf(start), Date.valueOf(finish))
        service.createConcertTour(toCreate).toString()
        return "Created tour $city $start - $finish"
    }

    @GetMapping
    fun getConcertTours(
        @RequestParam from: Int,
        @RequestParam size: Int
    ): String {
        return service.getConcertTours(Page(from, size)).map {
            "${it.city} ${it.start_date}" +
                    " ${it.finish_date}"
        }.joinToString("\n")
    }

    @PostMapping("/update/{id}")
    fun updateConcertTour(
        @PathVariable id: Int, @RequestParam city: String,
        @RequestParam start: String, @RequestParam finish: String
    ): String {
        val toCreate = ConcertTour(id, city, Date.valueOf(start), Date.valueOf(finish))
        val concertTour = service.findConcertTour(toCreate.id!!) ?: return "Concert tour with id $id not found"
        service.updateConcertTour(toCreate).toString()
        return "Updated tour from ${concertTour.city} ${concertTour.start_date} - ${concertTour.finish_date}" +
                "to $city, $start, $finish"
    }

    @PostMapping("/delete/{id}")
    fun deleteConcertTour(@PathVariable id: Int): String {
        service.deleteConcertTour(id).toString()
        return "Tour $id deleted"
    }

    @PostMapping("/link/{id}")
    fun addConcertTourToPerformance(@PathVariable id: Int, @RequestParam id2: Int): String {
        val performanceId = id2
        val concertTourId = id
        val performance = service.findPerformance(performanceId) ?: return "Performance with id $id not found"
        val concertTour = service.findConcertTour(concertTourId) ?: return "Concert tour with id $id2 not found"
        service.addConcertTourToPerformance(performanceId, concertTourId).toString()
        return "Tour $id + Performance $id2"
    }

    @GetMapping("/troupeBySpectacle/{id}")
    fun getTourTroupe(
        @PathVariable id: Int,
        @RequestParam start: String, @RequestParam finish: String
    ): String {
        val startD = Date.valueOf(start)
        val finishD = Date.valueOf(finish)
        return service.getTourTroupe(id, startD, finishD)
    }
}