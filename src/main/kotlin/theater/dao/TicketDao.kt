package theater.dao

import theater.model.Ticket
import theater.model.data.TicketData
import java.sql.Statement
import javax.sql.DataSource

class TicketDao(private val dataSource: DataSource) {

    fun createTicket(ticketData: TicketData): Int {
        val sql = if (ticketData.showId != null) {
            "INSERT INTO tickets(row, seat, price, presence, previously, show_id) VALUES (?, ?, ?, ?, ?, ?)"
        } else {
            "INSERT INTO tickets(row, seat, price, presence, previously) VALUES (?, ?, ?, ?, ?)"
        }
        val stmt = dataSource.connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, ticketData.row)
        stmt.setInt(2, ticketData.seat)
        stmt.setInt(3, ticketData.price)
        stmt.setBoolean(4, ticketData.presence)
        stmt.setBoolean(5, ticketData.previously)
        if (ticketData.showId != null) {
            stmt.setInt(6, ticketData.showId)
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

    fun getTicket(row: Int, seat: Int, price: Int, showId: Int): Ticket? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, row, seat, price, presence, previously, show_id" +
                        "FROM tickets" +
                        "WHERE row = ?, seat = ?, price = ?, show_id = ?"
        )
        stmt.setInt(1, row)
        stmt.setInt(1, seat)
        stmt.setInt(1, price)
        stmt.setInt(1, showId)
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