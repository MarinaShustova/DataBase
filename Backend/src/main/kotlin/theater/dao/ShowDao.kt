package theater.dao

import theater.model.Genre
import theater.model.Show
import theater.model.Spectacle
import theater.model.data.PlaybillData
import java.sql.Statement
import javax.sql.DataSource

class ShowDao(private val dataSource: DataSource) {

    fun createShow(show: Show): Int {
        val statement = if (show.performanceId != null) {
            "INSERT INTO shows (show_date, premiere, performance_id) VALUES (?, ?, ?)"
        } else {
            "INSERT INTO shows (show_date, premiere) VALUES (?, ?)"
        }
        val stmt = dataSource.connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)
        stmt.setTimestamp(1, show.date)
        stmt.setBoolean(2, show.premiere)
        if (show.performanceId != null) {
            stmt.setInt(3, show.performanceId)
        }
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun getShow(id: Int): Show? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, show_date, premiere, performance_id FROM shows WHERE id = ?"
        )
        stmt.setInt(1, id)
        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Show(queryResult.getInt("id"),
                    queryResult.getTimestamp("show_date"),
                    queryResult.getBoolean("premiere"),
                    queryResult.getInt("performance_id"))
        } else {
            null
        }
    }

    fun getShows(): ArrayList<Show> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, show_date, premiere, performance_id FROM shows"
        )
        val queryResult = stmt.executeQuery()

        val res = ArrayList<Show>()
        while (queryResult.next()) {
            res.add(Show(queryResult.getInt("id"),
                    queryResult.getTimestamp("show_date"),
                    queryResult.getBoolean("premiere"),
                    queryResult.getInt("performance_id")))
        }
        return res
    }

    fun updateShow(show: Show) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE shows SET show_date=?, premiere=?, performance_id = ? WHERE id = ? ",
                Statement.RETURN_GENERATED_KEYS)
        stmt.setTimestamp(1, show.date)
        stmt.setBoolean(2, show.premiere)
        stmt.setInt(3, show.id)
        stmt.setInt(4, show.performanceId)
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

    fun getPlaybills(): List<PlaybillData> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT shows.id show_id, shows.show_date date, shows.premiere premiere," +
                        " specs.name name, specs.age_category age\n" +
                        "FROM shows AS shows\n" +
                        "JOIN performances AS perfs ON shows.performance_id = perfs.id\n" +
                        "JOIN spectacles AS specs ON perfs.spectacle_id = specs.id\n"
        )
        val queryResult = stmt.executeQuery()

        val out = mutableListOf<PlaybillData>()
        while (queryResult.next()){
            val data = PlaybillData(
                    queryResult.getDate("date"),
                    queryResult.getBoolean("premiere"),
                    queryResult.getString("name"),
                    queryResult.getInt("age"),
                    queryResult.getInt("show_id")
            )
            out.add(data)
        }
        return out
    }
}