package theater.dao

import theater.model.Author
import theater.model.Country
import java.sql.Date
import java.sql.Statement
import javax.sql.DataSource

class AuthorDao(private val dataSource: DataSource) {

    fun createAuthor(author: Author): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO authors (surname, name, birth_date, death_date, country) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, author.surname)
        stmt.setString(2, author.name)
        stmt.setDate(3, author.birthDate)
        stmt.setDate(4, author.deathDate)
        stmt.setInt(5, author.country.id)
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }

    fun createAuthors(authors: Iterable<Author>): List<Int> {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO authors (surname, name, birth_date, death_date, country) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )
        for (author in authors) {
            stmt.setString(1, author.surname)
            stmt.setString(2, author.name)
            stmt.setDate(3, author.birthDate)
            stmt.setDate(4, author.deathDate)
            stmt.setInt(5, author.country.id)
            stmt.addBatch()
        }
        stmt.executeBatch()

        val generatedKeys = stmt.generatedKeys
        val result = ArrayList<Int>()
        while (generatedKeys.next()) {
            result += generatedKeys.getInt(1)
        }

        return result
    }

    fun updateAuthor(author: Author) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE authors SET surname=?, name=?, birth_date=?, death_date=?, country=? WHERE id = ? "
        )
        stmt.setString(1, author.surname)
        stmt.setString(2, author.name)
        stmt.setDate(3, author.birthDate)
        stmt.setDate(4, author.deathDate)
        stmt.setInt(5, author.country.id)
        stmt.setInt(6, author.id)
        stmt.executeUpdate()
    }

    fun getAuthor(id: Int): Author? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT a.id author_id, a.surname surname, a.name author_name, a.birth_date birth_date, a.death_date death_date" +
                        ", c.id country_id, c.name country_name"
                        + "FROM authors AS a"
                        + "JOIN countries AS c ON a.country = c.id "
                        + "WHERE a.id = ?"
        )
        stmt.setInt(1, id)

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Author(queryResult.getInt("author_id"), queryResult.getString("surname"),
                    queryResult.getString("author_name"), queryResult.getDate("birth_date"),
                    queryResult.getDate("death_date"), Country(queryResult.getInt("country_id"),
                    queryResult.getString("country_name")))
        } else {
            null
        }
    }

    fun getAuthorByFullName(name: String, surname: String): Author? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT a.id author_id, a.surname surname, a.name author_name, a.birth_date birth_date, a.death_date death_date" +
                        ", c.id country_id, c.name country_name"
                        + " FROM authors AS a"
                        + " JOIN countries AS c ON a.country = c.id "
                        + " WHERE (a.name = ?) AND (a.surname = ?)"
        )
        stmt.setString(1, name)
        stmt.setString(2, surname)

        val queryResult = stmt.executeQuery()
        return if (queryResult.next()) {
            Author(queryResult.getInt("author_id"), queryResult.getString("surname"),
                    queryResult.getString("author_name"), queryResult.getDate("birth_date"),
                    queryResult.getDate("death_date"), Country(queryResult.getInt("country_id"),
                    queryResult.getString("country_name")))
        } else {
            null
        }
    }

    fun getAuthorsOfCountry(country: Country): ArrayList<Author> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT a.id author_id, a.surname surname, a.name author_name, a.birth_date birth_date, a.death_date death_date" +
                        ", c.id country_id, c.name country_name\n" +
                        "FROM authors AS a\n" +
                        "JOIN countries AS c ON a.country = c.id\n" +
                        "WHERE c.name = ?"
        )
        stmt.setString(1, country.name)
        val authorsList = ArrayList<Author>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            authorsList.add(Author(queryResult.getInt("author_id"), queryResult.getString("surname"),
                    queryResult.getString("author_name"), queryResult.getDate("birth_date"),
                    queryResult.getDate("death_date"), Country(queryResult.getInt("country_id"),
                    queryResult.getString("country_name"))))
        }
        return authorsList
    }

    fun getAuthorsOfCurTimePeriod(startDate: Date, endDate: Date): ArrayList<Author> {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT a.id author_id, a.surname surname, a.name author_name, a.birth_date birth_date, a.death_date death_date" +
                        ", c.id country_id, c.name country_name\n" +
                        "FROM authors AS a\n" +
                        "JOIN countries AS c ON a.country = c.id\n" +
                        "WHERE (a.birth_date > ?) AND (a.death_date < ?)"
        )
        stmt.setDate(1, startDate)
        stmt.setDate(2, endDate)

        val authorsList = ArrayList<Author>()
        val queryResult = stmt.executeQuery()
        while (queryResult.next()) {
            authorsList.add(Author(queryResult.getInt("author_id"), queryResult.getString("surname"),
                    queryResult.getString("author_name"), queryResult.getDate("birth_date"),
                    queryResult.getDate("death_date"), Country(queryResult.getInt("country_id"),
                    queryResult.getString("country_name"))))
        }
        return authorsList
    }

    fun deleteAuthor(author: Author) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM authors WHERE id = ?"
        )
        stmt.setInt(1, author.id)
        stmt.executeUpdate()
    }
}