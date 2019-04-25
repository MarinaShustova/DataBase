package theater.dao

import theater.model.Show
import java.sql.Statement
import javax.sql.DataSource

class ShowDao(private val dataSource: DataSource) {

    fun createShow(show: Show): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO shows (show_date, premiere) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setTimestamp(1, show.date)
        stmt.setBoolean(2, show.premiere)
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun getShow(id: Int): Show? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, show_date, premiere FROM shows WHERE id = ?"
        )
        stmt.setInt(1, id)
        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Show(queryResult.getInt("id"),
                    queryResult.getTimestamp("show_date"),
                    queryResult.getBoolean("premiere"))
        } else {
            null
        }
    }

    fun updateShow(show: Show) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE shows SET show_date=?, premiere=? WHERE id = ? ",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setTimestamp(1, show.date)
        stmt.setBoolean(2, show.premiere)
        stmt.setInt(3, show.id)
        stmt.executeUpdate()
    }

    fun deleteShow(show: Show) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM shows WHERE id = ?"
        )
        stmt.setInt(1, show.id)
        stmt.executeUpdate()
    }

    fun deleteShow(showId: Int) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM shows WHERE id = ?"
        )
        stmt.setInt(1, showId)
        stmt.executeUpdate()
    }
}