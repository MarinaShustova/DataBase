package theater.service

import theater.dao.TicketDao
import theater.model.Ticket
import javax.sql.DataSource

class TicketService(private val dataSource: DataSource, private val ticketDao: TicketDao) : Service() {

    fun createTicket(ticket: Ticket): Int {
        return transaction(dataSource) {
            ticketDao.createTicket(ticket)
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

}