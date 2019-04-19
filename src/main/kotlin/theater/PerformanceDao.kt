package theater

import java.sql.Statement
import javax.sql.DataSource

data class Page(val num: Int, val size: Int)

class PerformanceDao(private val dataSource: DataSource) {

    fun createPerformance(toCreate: Performance): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO performances (production_designer, production_director, production_conductor, season) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.production_designer)
        stmt.setString(2, toCreate.production_director)
        stmt.setString(3, toCreate.production_conductor)
        stmt.setInt(4, toCreate.season)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun createPerformances(toCreate: Iterable<Performance>): List<Long> {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO performances (production_designer, production_director, production_conductor, season) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS

        )
        for (performance in toCreate) {
            stmt.setString(1, performance.production_designer)
            stmt.setString(2, performance.production_director)
            stmt.setString(3, performance.production_conductor)
            stmt.setInt(4, performance.season)
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

    fun deletePerformance(id: Long): Long {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM performances WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun findPerformance(id: Long): Performance? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "p.id pid, p.production_designer, p.production_director, p.production_conductor, " +
                    "p.season " +
                    "FROM performances AS p " +
                    "WHERE p.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Performance(
                rs.getLong("pid"), rs.getString("production_designer"),
                rs.getString("production_director"), rs.getString("production_conductor"),
                rs.getInt("season"))
        } else {
            null
        }
    }

    fun updatePerformance(performance: Performance) {
        val stmt = dataSource.connection.prepareStatement("UPDATE performances SET production_designer = ?, production_director = ?, production_conductor = ?, season = ? WHERE id = ?")
        stmt.setString(1, performance.production_designer)
        stmt.setString(2, performance.production_director)
        stmt.setString(3, performance.production_conductor)
        stmt.setInt(4, performance.season)
        stmt.setLong(5, performance.id!!)
        stmt.executeUpdate()
    }

    fun getPerformances(page: Page): List<Performance> {
        val theQuery = "SELECT id, production_designer, production_director, production_conductor, season  FROM performances ORDER BY season LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * page.num)

        val res = ArrayList<Performance>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(Performance(
                rs.getLong("id"), rs.getString("production_designer"),
                rs.getString("production_director"), rs.getString("production_conductor"),
                rs.getInt("season"))
            )
        }
        return res
    }

}
