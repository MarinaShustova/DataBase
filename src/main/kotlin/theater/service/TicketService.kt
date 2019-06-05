package theater.service

import theater.dao.TicketDao
import theater.exception.TicketNotFoundException
import theater.model.Ticket
import theater.model.data.TicketData
import javax.sql.DataSource

class TicketService(private val dataSource: DataSource, private val ticketDao: TicketDao) : Service() {

    fun createTicket(ticketData: TicketData): Int {
        return transaction(dataSource) {
            ticketDao.createTicket(ticketData)
        }
    }

    fun addTicketForShow(showId: Int, ticketId: Int) {
        return transaction(dataSource) {
            ticketDao.addTicketForShow(showId, ticketId)
        }
    }

    fun getTicket(id: Int): Ticket? {
        return transaction(dataSource) {
            ticketDao.getTicket(id)
        }
    }

    fun getTicketsOfShow(showId: Int): ArrayList<TicketData> {
        return transaction(dataSource) {
            ticketDao.getTicketsOfShow(showId)
        }
    }

    fun updateTicket(ticket: Ticket) {
        transaction(dataSource) {
            ticketDao.updateTicket(ticket)
        }
    }

    fun deleteTicket(ticket: Ticket) {
        transaction(dataSource) {
            ticketDao.deleteTicket(ticket)
        }
    }

    fun buyTicket(row: Int, seat: Int, price: Int, showId: Int, previously: Boolean) {
        transaction(dataSource) {
            val ticket = ticketDao.getTicket(row, seat, price, showId) ?: throw TicketNotFoundException()
            ticket.presence = false
            ticket.previously = previously
            ticketDao.updateTicket(ticket)
        }
    }
}