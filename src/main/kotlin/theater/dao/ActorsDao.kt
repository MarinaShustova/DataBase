package theater.dao

import theater.Info
import theater.model.Actor
import theater.model.Author
import theater.model.Country
import java.sql.Date
import java.sql.Statement
import java.util.*
import javax.sql.DataSource
import kotlin.collections.ArrayList
import theater.model.Producer


class ActorsDao(private val dataSource: DataSource) {
    fun createActor(toCreate: Actor): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO actors (employee_id, is_student) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, toCreate.employee.id)
        stmt.setBoolean(2, toCreate.isStudent)

        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun deleteActor(id: Long) {
        val stmt = dataSource.connection.prepareStatement("DELETE FROM actors WHERE actors.id = ?")
        stmt.setInt(1, id.toInt())
        stmt.executeUpdate()
    }

    fun updateActor(id: Long, keysNValues: Map<String, String>) { //updating in two statements
        val employeeProperties = keysNValues.asSequence().filter {
            it.key.equals("fio") || it.key.equals("sex") || it.key.equals("origin")
                    || it.key.equals("birth_date") || it.key.equals("hire_date")
                    || it.key.equals("salary") || it.key.equals("children_amount")
        }
        val actorProperties = keysNValues.asSequence().filter { it.key.equals("is_student") }

        var questionMarks = employeeProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        var stmt = dataSource.connection.prepareStatement(
            "UPDATE employees SET ${questionMarks} WHERE " +
                    "employees.id = (SELECT employee_id FROM actors WHERE actors.id = ?)"
        )

        var count = 1

        for (it in employeeProperties) {
            when (it.key) {
                "fio", "sex", "origin" -> {
                    stmt.setString(count++, it.value)
                }
                "birth_date", "hire_date" -> {
                    stmt.setDate(count++, Date.valueOf(it.value))
                }
                "salary", "children_amount" -> {
                    stmt.setInt(count++, it.value.toInt())
                }
            }
        }
        stmt.setLong(employeeProperties.toList().size + 1, id)
        stmt.executeUpdate()

        questionMarks = actorProperties.asSequence().map { "${it.key} = ?" }.joinToString(", ")

        stmt = dataSource.connection.prepareStatement(
            "UPDATE actors SET ${questionMarks} WHERE actors.id = ?"
        )

        count = 1
        for (it in actorProperties) {
            when (it.key) {
                "is_student" -> {
                    stmt.setBoolean(count++, it.value.toBoolean())
                }
            }
        }
        stmt.setLong(actorProperties.toList().size + 1, id)
        stmt.executeUpdate()
    }

    //functions for selections

    fun getActorsWithRanks(employeesDao: EmployeesDao): List<Actor> {
        val theQuery = "SELECT fio, r.name  FROM actors a " +
                "JOIN actors_ranks ar ON a.id = ar.actor_id " +
                "JOIN ranks r on ar.rank_id = r.id GROUP BY fio"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)

        val res = ArrayList<Actor>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }
        return res
    }

    fun getActorsWithRanksSex(employeesDao: EmployeesDao, sex: String): List<Actor> {
        val theQuery = "SELECT fio, r.name  FROM actors a " +
                "JOIN actors_ranks ar ON a.id = ar.actor_id " +
                "JOIN ranks r on ar.rank_id = r.id WHERE a.sex = ? GROUP BY fio"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setString(1, sex)

        val res = ArrayList<Actor>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }
        return res
    }

    fun getActorsWithRanksContests(employeesDao: EmployeesDao, contests : List<String>): List<Actor> {
        var questions = "("
        for (contest in contests){
            questions += " ?"
        }
        questions += ")"
        val theQuery = "SELECT fio, r.name  FROM actors a " +
                "JOIN actors_ranks ar ON a.id = ar.actor_id " +
                "JOIN ranks r on ar.rank_id = r.id WHERE r.contest in"+ questions +"GROUP BY fio"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        val i = 1;
        for (contest in contests){
            stmt.setString(i, contest)
        }

        val res = ArrayList<Actor>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }
        return res
    }

    fun getActorsWithRanksAge(employeesDao: EmployeesDao, age: Int): List<Actor> {
        val theQuery: String
        if (age > 0) {
            theQuery = "SELECT fio, r.name  FROM actors a " +
                    "JOIN actors_ranks ar ON a.id = ar.actor_id " +
                    "JOIN ranks r on ar.rank_id = r.id " +
                    "WHERE (SELECT DATE_PART('year', e.birthDate) - DATE_PART('year', ?)) >= ? GROUP BY fio"
        } else {
            theQuery = "SELECT fio, r.name  FROM actors a " +
                    "JOIN actors_ranks ar ON a.id = ar.actor_id " +
                    "JOIN ranks r on ar.rank_id = r.id " +
                    "WHERE (SELECT DATE_PART('year', e.birthDate) - DATE_PART('year', ?)) <= ? GROUP BY fio"
        }
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        val sqlDate = Date(Calendar.getInstance().getTime().getTime())
        stmt.setDate(1, sqlDate)
        if (age > 0) {
            stmt.setInt(2, age)
        } else {
            val age1 = (age).times(-1);
            stmt.setInt(2, age1)
        }

        val res = ArrayList<Actor>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }
        return res
    }

    fun getTourTroupe(employeesDao: EmployeesDao,
                      spectacleId: Int, start: Date, finish: Date): Pair<List<Actor>, List<Producer>> {
        var theQuery = "select a.fio from (select * from employees join actors on employees.id = actors.employee_id\n" +
                "    join actors_roles ar on actors.id = ar.actor_id join roles_performances rp on rp.role_id = ar.role_id\n" +
                "    join tours_performances tp on tp.performance_id = rp.performance_id join performances p2 on rp.performance_id = p2.id\n" +
                "join tours t on p2.id = t.performance_id\n" +
                "where p2.spectacle_id = ? and t.start_date >= ? and t.finish_date <= ?) as a"
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
        theQuery = "select a.fio\n" +
                "from (select *\n" +
                "      from employees\n" +
                "               join producers on employees.id = producers.employee_id\n" +
                "               join performances p on producers.id = p.production_conductor or producers.id = p.production_designer\n" +
                "          or producers.id = p.production_director\n" +
                "               join tours_performances tp on tp.performance_id = p.id\n" +
                "               join tours t on p.id = t.performance_id\n" +
                "      where p.spectacle_id = ?\n" +
                "        and t.start_date >= ?\n" +
                "        and t.finish_date <= ?) as a\n"
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
                    employeesDao.getEmployee(rs.getLong("employee"))!!, "prod"
                    //TODO: producer's activity
                )
            )
        }
        return Pair(act, prod)
    }

    fun getPerformanceInfo(employeesDao: EmployeesDao,
                      spectacleId: Int, countryDao: CountryDao): Info {
        var theQuery = "with premiere as (select s.show_date, p.id " +
                "                  from performances p " +
                "                           join shows s on p.id = s.performance_id " +
                "                  where s.premiere = true " +
                "                    and p.spectacle_id = ?)" +
                "select fio, show_date " +
                "from performances p " +
                "         join premiere pr on pr.id = p.id " +
                "         join producers p2 on p.production_conductor = p2.id " +
                "    or p.production_designer = p2.id " +
                "    or p.production_director = p2.id " +
                "         join employees e on p2.employee_id = e.id"
        val conn = dataSource.connection
        var stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        var rs = stmt.executeQuery()

        val prod = ArrayList<Producer>()
        while (rs.next()) {
            prod.add(
                Producer(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!, "prod"
                    //TODO: producer's activity
                )
            )
        }

        theQuery = "with premiere as (select s.show_date, p.id\n" +
                "                  from performances p\n" +
                "                           join shows s on p.id = s.performance_id\n" +
                "                  where s.premiere = true\n" +
                "                    and p.spectacle_id = ?)\n" +
                "select fio, show_date\n" +
                "from performances p\n" +
                "         join premiere pr on pr.id = p.id\n" +
                "         join roles_performances rp on p.id = rp.performance_id\n" +
                "         join roles r on rp.role_id = r.id\n" +
                "         join actors_roles ar on r.id = ar.role_id\n" +
                "         join actors a on ar.actor_id = a.id\n" +
                "         join employees e on a.employee_id = e.id"
        stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        val act = ArrayList<Actor>()
        rs = stmt.executeQuery()
        while (rs.next()) {
            act.add(
                Actor(
                    rs.getLong("id"),
                    employeesDao.getEmployee(rs.getLong("employee"))!!,
                    rs.getBoolean("isStudent")
                )
            )
        }

        theQuery = "select * from authors a join spectacles s " +
                "on a.id = s.author_id where s.id = ?"
        stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, spectacleId)
        val author : Author
        rs = stmt.executeQuery()
        if (rs.next()) {
            author = Author(rs.getInt("id"), rs.getString("name"),
                rs.getString("surname"), rs.getDate("birthDare"),
                rs.getDate("deathDate"), countryDao.getCountry(rs.getInt("country"))!!)
        }
        else author = Author(-1, "Default", "Default", Date(0),
            Date(0), Country(-1, "Default")
        )
        return Info(prod, act, author)
    }
}