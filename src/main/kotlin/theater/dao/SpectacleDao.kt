package theater.dao

import theater.model.*
import java.sql.Statement
import java.sql.Timestamp
import java.sql.Date
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class SpectacleDao(private val dataSource: DataSource) {

    fun createSpectacle(spectacle: Spectacle): Int {
        val statement = if (spectacle.author != null) {
            "INSERT INTO spectacles(genre, name, age_category, author_id) VALUES (?, ?, ?, ?)"
        } else {
            "INSERT INTO spectacles(genre, name, age_category) VALUES (?, ?, ?)"
        }
        val stmt = dataSource.connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)
        stmt.setInt(1, spectacle.genre.id)
        stmt.setString(2, spectacle.name)
        stmt.setInt(3, spectacle.ageCategory)
        val author = spectacle.author
        if (author != null) {
            stmt.setInt(4, author)
        }
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun createAuthorOfSpectacle(spectacle: Spectacle, author: Author): Int {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE spectacles SET author_id = ? WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS
        )

        stmt.setInt(1, author.id)
        stmt.setInt(2, spectacle.id)

        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun getSpectacle(id: Int): Spectacle? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.genre spect_g_id, " +
                        "s.age_category spect_age, g.name g_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "JOIN genres AS g ON s.genre = g.id\n" +
                        "LEFT JOIN authors AS a ON a.id = s.author_id\n" +
                        "WHERE s.id = ?"
        )
        stmt.setInt(1, id)

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_g_id"), queryResult.getString("g_name")),
                    queryResult.getInt("spect_age"))
            val author: Int = queryResult.getInt("author_id")
            return if (author == 0) {
                spectacle
            } else {
                spectacle.author = author
                spectacle
            }
        } else {
            null
        }
    }

    fun getSpectacles(): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.genre spect_g_id, " +
                        "s.age_category spect_age, g.name g_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "JOIN genres AS g ON s.genre = g.id\n" +
                        "LEFT JOIN authors AS a ON a.id = s.author_id"
        )
        val queryResult = stmt.executeQuery()

        val res = ArrayList<Spectacle>()
        while (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val authorId: Int = queryResult.getInt("author_id")
            if (authorId != 0) {
                spectacle.author = authorId
            }
            res.add(spectacle)
        }
        return res
    }

    fun getSpectacleByName(name: String): Spectacle? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.genre spect_genre, s.age_category spect_age," +
                        " g.name genre_name, s.author_id\n" +
                        "FROM spectacles AS s \n" +
                        "\tJOIN genres AS g ON s.genre = g.id \n" +
                        "WHERE s.name = ?"
        )
        stmt.setString(1, name)

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val author: Int = queryResult.getInt("author_id")
            return if (author == 0) {
                spectacle
            } else {
                spectacle.author = author
                spectacle
            }
        } else {
            null
        }
    }

    fun getSpectaclesOfGenre(genre: Genre): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, s.genre spect_genre," +
                        " g.name genre_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN genres AS g ON g.id = s.genre\n" +
                        " WHERE g.name = ?"
        )
        stmt.setString(1, genre.name)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val authorId: Int = queryResult.getInt("author_id")
            if (authorId != 0) {
                spectacle.author = authorId
            }
            res.add(spectacle)
        }
        return res
    }

    fun getSpectaclesOfAuthor(author: Author): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, " +
                        "s.genre spect_genre, g.name genre_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN genres AS g ON g.id = s.genre\n" +
                        "WHERE author_id = ?"
        )
        stmt.setInt(1, author.id)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val authorId: Int = queryResult.getInt("author_id")
            if (authorId != 0) {
                spectacle.author = authorId
            }
            res.add(spectacle)
        }
        return res
    }

    fun getSpectaclesOfCountry(country: Country): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, " +
                        "s.genre spect_genre, g.name genre_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN genres AS g ON g.id = s.genre\n" +
                        "\tLEFT JOIN authors AS a ON a.id = s.author_id\n" +
                        "WHERE a.country = ?"
        )
        stmt.setInt(1, country.id)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val author: Int = queryResult.getInt("author_id")
            if (author != 0) {
                spectacle.author = author
            }
            res.add(spectacle)
        }
        return res
    }

    fun getSpectaclesOfCurAuthorLifePeriod(dateFrom: Timestamp, dateTo: Timestamp): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, " +
                        "s.genre spect_genre, g.name genre_name, s.author_id\n" +
                        "FROM spectacles AS s\n" +
                        "\tLEFT JOIN authors AS a ON s.author_id = a.id\n" +
                        "\tJOIN genres AS g ON g.id = s.genre\n" +
                        "WHERE ((a.birth_date > ?) AND (a.birth_date < ?)) " +
                        "OR ((a.death_date > ?) AND (a.death_date < ?))"
        )
        stmt.setTimestamp(1, dateFrom)
        stmt.setTimestamp(2, dateTo)
        stmt.setTimestamp(3, dateFrom)
        stmt.setTimestamp(4, dateTo)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            val spectacle = Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
            val author: Int = queryResult.getInt("author_id")
            if (author != 0) {
                spectacle.author = author
            }
            res.add(spectacle)
        }
        return res
    }

    fun updateSpectacle(spectacle: Spectacle) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE spectacles SET name = ?, genre=?, age_category=? WHERE id = ?"
        )
        stmt.setString(1, spectacle.name)
        stmt.setInt(2, spectacle.genre.id)
        stmt.setInt(3, spectacle.ageCategory)
        stmt.setInt(4, spectacle.id)
        stmt.executeUpdate()
    }

    fun deleteSpectacle(spectacle: Spectacle) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM spectacles WHERE id = ?"
        )
        stmt.setInt(1, spectacle.id)
        stmt.executeUpdate()
    }

    fun deleteSpectacle(spectacleId: Int) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM spectacles WHERE id = ?"
        )
        stmt.setInt(1, spectacleId)
        stmt.executeUpdate()
    }

    fun getSpectacleProfitByPeriod(spectacleId: Int, periodStart: Date, periodEnd: Date): Int {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT COUNT(t.price) as profit " +
                        "FROM (SELECT * FROM spectacles WHERE spectacles.id = ?) sp  " +
                        "JOIN performances p ON p.spectacle_id = sp.id " +
                        "JOIN shows sh on sh.performance_id = p.id  " +
                        "JOIN tickets t on t.show_id = sh.id " +
                        "WHERE sh.show_date > ? AND sh.show_date < ? AND NOT t.presence"
        )
        stmt.setInt(1, spectacleId)
        stmt.setDate(2, periodStart)
        stmt.setDate(3, periodEnd)
        val res = stmt.executeQuery()
        return res.getInt("profit")
    }

    fun getSpectacleProfit(spectacleId: Int): Int {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT COUNT(t.price) as profit " +
                        "FROM (SELECT * FROM spectacles WHERE spectacles.id = ?) sp  " +
                        "JOIN performances p ON p.spectacle_id = sp.id " +
                        "JOIN shows sh on sh.performance_id = p.id  " +
                        "JOIN tickets t on t.show_id = sh.id " +
                        "WHERE NOT t.presence"
        )
        stmt.setInt(1, spectacleId)
        val res = stmt.executeQuery()
        return res.getInt("profit")
    }

}