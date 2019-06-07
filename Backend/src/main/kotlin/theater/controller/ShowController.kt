package theater.controller

import org.springframework.web.bind.annotation.*
import theater.exception.ShowNotFoundException
import theater.model.Show
import theater.model.data.PlaybillData
import theater.model.data.ShowData
import theater.service.ShowService

@RestController
@RequestMapping("/shows")
class ShowController(private val showService: ShowService) {

    @PostMapping("/create")
    fun createShow(@RequestBody showData: ShowData) {
        showService.createShow(showData)
    }

    @PostMapping("/update/{id}")
    fun updateShow(@PathVariable id: Int, @RequestBody showData: ShowData): String {
        showService.updateShow(id, showData)
        return "Show updated"
    }

    @GetMapping("/{id}")
    fun getShow(@PathVariable id: Int): Show {
        return showService.getShow(id) ?: throw ShowNotFoundException()
    }

    @GetMapping("/get")
    fun getShows(): ArrayList<Show> {
        return showService.getShows()
    }

    @PostMapping("/delete/{id}")
    fun deleteShow(@PathVariable id: Int): String {
        showService.deleteShow(id)
        return "Deleted show argsStr"
    }

    @GetMapping("/playbills")
    fun getPlaybills(): List<PlaybillData> {
        return showService.getPlaybills()
    }
}