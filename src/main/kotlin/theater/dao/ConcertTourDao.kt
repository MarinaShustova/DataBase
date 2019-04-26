package theater.dao

import theater.model.ConcertTour
import java.sql.Statement
import javax.sql.DataSource

class ConcertTourDao(private val dataSource: DataSource) {

    fun createConcertTour(toCreate: ConcertTour): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO concert_tours (city, start_date, finish_date) VALUES (?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.city)
        stmt.setDate(2, toCreate.start_date)
        stmt.setDate(3, toCreate.finish_date)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun createConcertTours(toCreate: Iterable<ConcertTour>): List<Long> {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO concert_tours (city, start_date, finish_date) VALUES (?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS

        )
        for (tour in toCreate) {
            stmt.setString(1, tour.city)
            stmt.setDate(2, tour.start_date)
            stmt.setDate(3, tour.finish_date)
            stmt.addBatch()
        }

        stmt.executeBatch()
        val gk = stmt.generatedKeys
        val res = ArrayList<Long>()
        while (gk.next()) {
            res += gk.getLong(1)
        }

        return res
    }

    fun findConcertTour(id: Long): ConcertTour? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "ct.id ctid, ct.city, ct.start_date, ct.finish_date " +
                    "FROM concert_tours AS ct " +
                    "WHERE ct.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            ConcertTour(
                rs.getLong("ctid"), rs.getString("city"),
                rs.getDate("start_date"), rs.getDate("finish_date")
            )
        } else {
            null
        }
    }

    fun updateConcertTour(tour: ConcertTour) {
        val stmt =
            dataSource.connection.prepareStatement("UPDATE concert_tours SET city = ?, start_date = ?, finish_date = ? WHERE id = ?")
        stmt.setString(1, tour.city)
        stmt.setDate(2, tour.start_date)
        stmt.setDate(3, tour.finish_date)
        stmt.setLong(5, tour.id!!)
        stmt.executeUpdate()
    }

    fun deleteConcertTour(id: Long): Long {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM concert_tours WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun getConcertTours(page: Page): List<ConcertTour> {
        val theQuery =
            "SELECT id, city, start_date, finish_date  FROM concert_tours ORDER BY start_date LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * page.num)

        val res = ArrayList<ConcertTour>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                ConcertTour(
                    rs.getLong("id"), rs.getString("city"),
                    rs.getDate("start_date"), rs.getDate("finish_date")
                )
            )
        }
        return res
    }

}
