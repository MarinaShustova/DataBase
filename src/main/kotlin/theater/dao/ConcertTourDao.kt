package theater.dao

import theater.model.Actor
import theater.model.ConcertTour
import theater.model.Producer
import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource

class ConcertTourDao(private val dataSource: DataSource) {

    fun createConcertTour(toCreate: ConcertTour): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO tours (city, start_date, finish_date) VALUES (?, ?, ?)",
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
            "INSERT INTO tours (city, start_date, finish_date) VALUES (?, ?, ?)",
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
                    "FROM tours AS ct " +
                    "WHERE ct.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            ConcertTour(
                rs.getInt("ctid"), rs.getString("city"),
                rs.getDate("start_date"), rs.getDate("finish_date")
            )
        } else {
            null
        }
    }

    fun updateConcertTour(tour: ConcertTour) {
        val stmt =
            dataSource.connection.prepareStatement("UPDATE tours SET city = ?, start_date = ?, finish_date = ? WHERE id = ?")
        stmt.setString(1, tour.city)
        stmt.setDate(2, tour.start_date)
        stmt.setDate(3, tour.finish_date)
        stmt.setInt(5, tour.id!!)
        stmt.executeUpdate()
    }

    fun deleteConcertTour(id: Long): Long {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM tours WHERE id = ?",
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
            "SELECT id, city, start_date, finish_date  FROM tours ORDER BY start_date LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * page.num)

        val res = ArrayList<ConcertTour>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                ConcertTour(
                    rs.getInt("id"), rs.getString("city"),
                    rs.getDate("start_date"), rs.getDate("finish_date")
                )
            )
        }
        return res
    }

    fun getTourTroupe(employeesDao: EmployeesDao,
                      spectacleId: Int, start: Date, finish: Date
    ): Pair<List<Actor>, List<Producer>> {
        var theQuery = "select a.fio, employee_id, is_student\n" +
                "from (select *\n" +
                "      from employees\n" +
                "               join actors on employees.id = actors.employee_id\n" +
                "               join actors_roles ar on actors.id = ar.actor_id\n" +
                "               join roles_performances rp on rp.role_id = ar.role_id\n" +
                "               join performances p2 on rp.performance_id = p2.id\n" +
                "               join tours t on p2.id = t.performance_id\n" +
                "      where p2.spectacle_id = ?\n" +
                "        and t.start_date >= ?\n" +
                "        and t.finish_date <= ?) as a"
        val conn = dataSource.connection
        var stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        stmt.setDate(2, start)
        stmt.setDate(3, finish)

        val act = ArrayList<Actor>()
        var rs = stmt.executeQuery()
        while (rs.next()) {
            act.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }
        theQuery = "select *\n" +
                "from (select fio, employee_id as \"employee\", activity, producers.id\n" +
                "      from employees\n" +
                "               join producers on employees.id = producers.employee_id\n" +
                "               join performances p on producers.id = p.production_conductor or producers.id = p.production_designer\n" +
                "          or producers.id = p.production_director\n" +
                "               join tours t on p.id = t.performance_id\n" +
                "      where p.spectacle_id = ?\n" +
                "        and t.start_date >= ?\n" +
                "        and t.finish_date <= ?) as a"
        stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        stmt.setDate(2, start)
        stmt.setDate(3, finish)

        val prod = ArrayList<Producer>()
        rs = stmt.executeQuery()
        while (rs.next()) {
            prod.add(
                Producer(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getString("activity")
                )
            )
        }
        return Pair(act, prod)
    }

}
