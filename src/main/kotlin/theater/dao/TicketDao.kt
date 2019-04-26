package theater.dao

import theater.model.Ticket
import java.sql.Statement
import javax.sql.DataSource

class TicketDao(private val dataSource: DataSource) {

    fun createTicket(ticket: Ticket): Int {
        val sql = if (ticket.showId != null) {
            "INSERT INTO tickets(row, seat, price, presence, previously, show_id) VALUES (?, ?, ?, ?, ?, ?)"
        } else {
            "INSERT INTO tickets(row, seat, price, presence, previously) VALUES (?, ?, ?, ?, ?)"
        }
        val stmt = dataSource.connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, ticket.row)
        stmt.setInt(2, ticket.seat)
        stmt.setInt(3, ticket.price)
        stmt.setBoolean(4, ticket.presence)
        stmt.setBoolean(5, ticket.previously)
        if (ticket.showId != null) {
            stmt.setInt(6, ticket.showId)
        }
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun getTicket(id: Int): Ticket? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, row, seat, price, presence, previously, show_id" +
                        "FROM tickets" +
                        "WHERE id = ?"
        )
        stmt.setInt(1, id)
        val queryResult = stmt.executeQuery()

        return if (queryResult.next()) {
            Ticket(queryResult.getInt("id"),
                    queryResult.getInt("row"),
                    queryResult.getInt("seat"),
                    queryResult.getInt("price"),
                    queryResult.getBoolean("presence"),
                    queryResult.getBoolean("previously"),
                    queryResult.getInt("show_id"))
        } else {
            null
        }
    }

    fun addTicketForShow(ticketId: Int, showId: Int) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE tickets SET show_id = ? WHERE id = ?"
        )
        stmt.setInt(1, showId)
        stmt.setInt(2, ticketId)
        stmt.executeUpdate()
    }

    fun updateTicket(ticket: Ticket) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE tickets SET row = ?, seat = ?, price = ?, presence = ?, previously = ? WHERE id = ?"
        )
        stmt.setInt(1, ticket.row)
        stmt.setInt(2, ticket.seat)
        stmt.setInt(3, ticket.price)
        stmt.setBoolean(4, ticket.presence)
        stmt.setBoolean(5, ticket.previously)
        stmt.setInt(6, ticket.id)
        stmt.executeUpdate()
    }

    fun deleteTicket(ticket: Ticket) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM tickets WHERE id = ?"
        )
        stmt.setInt(1, ticket.id)
        stmt.executeQuery()
    }

}