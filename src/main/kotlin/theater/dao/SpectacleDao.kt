package theater.dao

import theater.model.Author
import theater.model.Country
import theater.model.Genre
import theater.model.Spectacle
import java.sql.Statement
import java.sql.Timestamp
import javax.sql.DataSource

class SpectacleDao(private val dataSource: DataSource) {

    fun createSpectacle(spectacle: Spectacle): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO spectacles(genre, name, age_category) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, spectacle.genre.id)
        stmt.setString(2, spectacle.name)
        stmt.setInt(3, spectacle.ageCategory)
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun createAuthorOfSpectacle(spectacle: Spectacle, author: Author): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO authors_spectacles(spectacle_id, author_id) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )

        stmt.setInt(1, spectacle.id)
        stmt.setInt(2, author.id)

        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun getSpectacle(id: Int): Spectacle? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id, s.name, s.genre, s.age_category, g.name" +
                        "FROM spectacles AS s" +
                        "JOIN genres AS g ON s.genre = g.id" +
                        "WHERE s.id = ?"
        )
        stmt.setInt(1, id)
        stmt.executeQuery()

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Spectacle(queryResult.getInt("s.id"),
                    queryResult.getString("s.name"),
                    Genre(queryResult.getInt("s.genre"), queryResult.getString("g.name")),
                    queryResult.getInt("s.age_category"))
        } else {
            null
        }
    }

    fun getSpectacleByName(name: String): Spectacle? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.genre spect_genre, s.age_category spect_age, g.name genre_name " +
                        "FROM spectacles AS s " +
                        "JOIN genres AS g ON s.genre = g.id " +
                        "WHERE s.name = ?"
        )
        stmt.setString(1, name)

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Spectacle(queryResult.getInt("spect_id"),
                    queryResult.getString("spect_name"),
                    Genre(queryResult.getInt("spect_genre"), queryResult.getString("genre_name")),
                    queryResult.getInt("spect_age"))
        } else {
            null
        }
    }

    fun getSpectacleOfGenre(genre: Genre): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, s.genre spect_genre, g.name genre_name \n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN genres AS g ON g.id = s.genre\n" +
                        "WHERE genres.name = ?"
        )
        stmt.setString(1, genre.name)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            res.add(Spectacle(queryResult.getInt("s.id"),
                    queryResult.getString("s.name"),
                    Genre(queryResult.getInt("s.genre"), queryResult.getString("g.name")),
                    queryResult.getInt("s.age_category")))
        }
        return res
    }

    fun getSpectacleOfAuthor(author: Author): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, s.genre spect_genre, g.name genre_name\n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN authors_spectacles ON spectacles.id = authors_spectacles.spectacle_id\n" +
                        "\tJOIN authors ON authors_spectacles.author_id = authors.id\n" +
                        "WHERE authors.name = ?"
        )
        stmt.setString(1, author.name)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            res.add(Spectacle(queryResult.getInt("s.id"),
                    queryResult.getString("s.name"),
                    Genre(queryResult.getInt("s.genre"), queryResult.getString("g.name")),
                    queryResult.getInt("s.age_category")))
        }
        return res
    }

    fun getSpectacleOfCountry(country: Country): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT s.id spect_id, s.name spect_name, s.age_category spect_age, s.genre spect_genre, g.name genre_name\n" +
                        "FROM spectacles AS s\n" +
                        "\tJOIN authors_spectacles ON spectacles.id = authors_spectacles.spectacle_id\n" +
                        "\tJOIN authors ON authors_spectacles.author_id = authors.id\n" +
                        "WHERE authors.country = ?"
        )
        stmt.setString(1, country.name)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            res.add(Spectacle(queryResult.getInt("s.id"),
                    queryResult.getString("s.name"),
                    Genre(queryResult.getInt("s.genre"), queryResult.getString("g.name")),
                    queryResult.getInt("s.age_category")))
        }
        return res
    }

    fun getSpectacleOfCurAuthorLifePeriod(dateFrom: Timestamp, dateTo: Timestamp): ArrayList<Spectacle> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT spectacles.name\n" +
                        "FROM spectacles\n" +
                        "\tJOIN authors_spectacles ON spectacles.id = authors_spectacles.spectacle_id\n" +
                        "\tJOIN authors ON authors_spectacles.author_id = authors.id\n" +
                        "WHERE (authors.birth_date > ?) AND (authors.death_date < ?)"
        )
        stmt.setTimestamp(1, dateFrom)
        stmt.setTimestamp(2, dateTo)
        val res = ArrayList<Spectacle>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            res.add(Spectacle(queryResult.getInt("s.id"),
                    queryResult.getString("s.name"),
                    Genre(queryResult.getInt("s.genre"), queryResult.getString("g.name")),
                    queryResult.getInt("s.age_category")))
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
        stmt.executeQuery()
    }

    fun deleteSpectacle(spectacleId: Int) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM spectacles WHERE id = ?"
        )
        stmt.setInt(1, spectacleId)
        stmt.executeQuery()
    }
}