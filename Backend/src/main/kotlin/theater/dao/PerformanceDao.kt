package theater.dao

import theater.Info
import java.sql.Statement
import javax.sql.DataSource
import theater.model.*
import java.sql.Date

data class Page(val num: Int, val size: Int)

class PerformanceDao(private val dataSource: DataSource) {

    fun createPerformance(toCreate: Performance): Int {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO performances (production_designer, production_director, production_conductor, season) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, toCreate.production_designer)
        stmt.setInt(2, toCreate.production_director)
        stmt.setInt(3, toCreate.production_conductor)
        stmt.setInt(4, toCreate.season)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun createPerformances(toCreate: Iterable<Performance>): List<Int> {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO performances (production_designer, production_director, production_conductor, season) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS

        )
        for (performance in toCreate) {
            stmt.setInt(1, performance.production_designer)
            stmt.setInt(2, performance.production_director)
            stmt.setInt(3, performance.production_conductor)
            stmt.setInt(4, performance.season)
            stmt.addBatch()
        }

        stmt.executeBatch()
        val gk = stmt.generatedKeys
        val res = ArrayList<Int>()
        while (gk.next()) {
            res += gk.getInt(1)
        }

        return res
    }

    fun deletePerformance(id: Int): Int {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM performances WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun findPerformance(id: Int): Performance? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "p.id pid, p.production_designer, p.production_director, p.production_conductor, " +
                    "p.season " +
                    "FROM performances AS p " +
                    "WHERE p.id = ?"
        )
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
                Performance(
                    rs.getInt("pid"), rs.getInt("production_designer"),
                    rs.getInt("production_director"), rs.getInt("production_conductor"),
                    rs.getInt("season")
                )
        } else {
            null
        }
    }

    fun updatePerformance(performance: Performance) {
        val stmt =
            dataSource.connection.prepareStatement("UPDATE performances SET production_designer = ?, production_director = ?, production_conductor = ?, season = ? WHERE id = ?")
        stmt.setInt(1, performance.production_designer)
        stmt.setInt(2, performance.production_director)
        stmt.setInt(3, performance.production_conductor)
        stmt.setInt(4, performance.season)
        stmt.setInt(5, performance.id!!)
        stmt.executeUpdate()
    }

    fun getPerformances(page: Page): List<PerformanceData> {
        val theQuery =
            "SELECT id, production_designer, production_director, production_conductor, season  FROM performances LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * (page.num - 1))

        val res = ArrayList<PerformanceData>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                PerformanceData(
                    Performance(
                        rs.getInt("id"), rs.getInt("production_designer"),
                        rs.getInt("production_director"), rs.getInt("production_conductor"),
                        rs.getInt("season")
                    )
                )
            )
        }
        return res
    }

    fun addConcertTourToPerformance(performanceId: Int, concertTourId: Int) {
        val stmt =
            dataSource.connection.prepareStatement("INSERT INTO tours_performances (performance_id, tour_id) VALUES (?,?)")
        stmt.setInt(1, performanceId)
        stmt.setInt(2, concertTourId)
        stmt.executeUpdate()
    }

    fun addRoleToPerformance(performanceId: Int, roleId: Int) {
        val stmt =
            dataSource.connection.prepareStatement("INSERT INTO roles_performances (performance_id, role_id) VALUES (?,?)")
        stmt.setInt(1, performanceId)
        stmt.setInt(2, roleId)
        stmt.executeUpdate()
    }

    fun getPerformanceInfo(
        employeesDao: EmployeesDao,
        spectacleId: Int, countryDao: CountryDao
    ): Info {
        var theQuery = "with premiere as (select s.show_date, p.id\n" +
                "                  from performances p\n" +
                "                           join shows s on p.id = s.performance_id\n" +
                "                  where s.premiere = true\n" +
                "                    and p.spectacle_id = ?)\n" +
                "select fio, p2.id, activity\n" +
                "from performances p\n" +
                "         join premiere pr on pr.id = p.id\n" +
                "         join producers p2 on p.production_conductor = p2.id\n" +
                "    or p.production_designer = p2.id\n" +
                "    or p.production_director = p2.id\n" +
                "         join employees e on p2.employee_id = e.id"
        val conn = dataSource.connection
        var stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        var rs = stmt.executeQuery()

        val prod = ArrayList<Producer>()
        while (rs.next()) {
            prod.add(
                Producer(
                    rs.getInt("id"),
                    employeesDao.getEmployeeById(rs.getInt("employee"))!!, rs.getString("activity")
                )
            )
        }

        theQuery = "with premiere as (select s.show_date, p.id\n" +
                "                  from performances p\n" +
                "                           join shows s on p.id = s.performance_id\n" +
                "                  where s.premiere = true\n" +
                "                    and p.spectacle_id = ?)\n" +
                "select fio, show_date, e.id as \"employee\", a.id, is_student\n" +
                "from performances p\n" +
                "         join premiere pr on pr.id = p.id\n" +
                "         join roles_performances rp on p.id = rp.performance_id\n" +
                "         join roles r on rp.role_id = r.id\n" +
                "         join actors_roles ar on r.id = ar.role_id\n" +
                "         join actors a on ar.actor_id = a.id\n" +
                "         join employees e on a.employee_id = e.id;"
        stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        val act = ArrayList<Actor>()
        var date = Date(0)
        rs = stmt.executeQuery()
        while (rs.next()) {
            date = rs.getDate("show_date")
            act.add(
                Actor(
                    rs.getInt("id"),
                    employeesDao.getEmployeeById(rs.getInt("employee"))!!,
                    rs.getBoolean("is_student")
                )
            )
        }

        theQuery = "select a.id, a.name, surname, birth_date,\n" +
                "       death_date, country from authors a join spectacles s on a.id = s.author_id where s.id = ?"
        stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        val author: Author
        rs = stmt.executeQuery()
        if (rs.next()) {
            author = Author(
                rs.getInt("id"), rs.getString("name"),
                rs.getString("surname"), rs.getDate("birthDare"),
                rs.getDate("deathDate"), countryDao.getCountry(rs.getInt("country"))!!
            )
        } else author = Author(
            -1, "Default", "Default", Date(0),
            Date(0), Country(-1, "Default")
        )
        return Info(prod, act, author, date)
    }

}
