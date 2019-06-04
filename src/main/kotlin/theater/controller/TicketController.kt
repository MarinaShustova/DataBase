package theater.controller

import org.springframework.web.bind.annotation.RestController
import theater.model.Ticket
import theater.service.TicketService

@RestController
class TicketController(private val ticketService: TicketService) {

    fun createTicket(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size < 5) {
            return "at least 5 arg expected"
        }

        val showId = if (args.size == 6) {
            args[5].toInt()
        } else {
            null
        }
        val ticket = Ticket(-1, args[0].toInt(), args[1].toInt(), args[2].toInt(),
                args[3].toBoolean(), args[4].toBoolean(), showId)
        val ticketId = ticketService.createTicket(ticket)
        return "Created ticket: $ticketId, row = ${ticket.row}, place = ${ticket.seat}, price = ${ticket.price}, " +
                "${ticket.presence}, ${ticket.previously}, show = $showId"
    }

    fun addTicketForShow(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected"
        }

        ticketService.addTicketForShow(args[0].toInt(), args[1].toInt())
        return "Created ticket for show"
    }


}