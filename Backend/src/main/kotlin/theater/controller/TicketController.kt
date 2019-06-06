package theater.controller

import org.springframework.web.bind.annotation.*
import theater.model.data.TicketData
import theater.service.TicketService

@RestController
@RequestMapping("/tickets")
class TicketController(private val ticketService: TicketService) {

    @PostMapping("/create")
    fun createTicket(@RequestBody ticketData: TicketData): String {
        val ticketId = ticketService.createTicket(ticketData)
        return "Created ticket: $ticketId, row = ${ticketData.row}, place = ${ticketData.seat}, price = ${ticketData.price}, " +
                "${ticketData.presence}, ${ticketData.previously}, show = ${ticketData.showId}"
    }

    @PostMapping("/add/show")
    fun addTicketForShow(@RequestParam showId: Int, @RequestParam ticketId: Int): String {
        ticketService.addTicketForShow(showId, ticketId)
        return "Created ticket for show"
    }

    @GetMapping("/get/show")
    fun getTicketsOfShow(@RequestParam showId: Int): ArrayList<TicketData> {
        return ticketService.getTicketsOfShow(showId)
    }

    @PostMapping("/buy")
    fun buyTicket(@RequestParam row: Int, @RequestParam seat: Int, @RequestParam price: Int, @RequestParam showId: Int,
                  @RequestParam previously: Boolean) {
        ticketService.buyTicket(row, seat, price, showId, previously)
    }

}