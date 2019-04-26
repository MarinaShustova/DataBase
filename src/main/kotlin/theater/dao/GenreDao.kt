package theater.dao

import theater.model.Genre
import java.sql.Statement
import javax.sql.DataSource

class GenreDao(private val dataSource: DataSource) {

    fun createGenre(genre: Genre): Int {
        val stmt = dataSource.connection.prepareStatement(
                "INSERT INTO genres(name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, genre.name)
        stmt.executeUpdate()

        val generatedKeys = stmt.generatedKeys
        generatedKeys.next()

        return generatedKeys.getInt(1)
    }


    fun getGenre(id: Int): Genre? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, name FROM genres WHERE id = ?"
        )
        stmt.setInt(1, id)
        val queryResult = stmt.executeQuery()

        return if (queryResult.next()) {
            Genre(queryResult.getInt("id"), queryResult.getString("name"))
        } else {
            null
        }
    }

    fun getGenreByName(name: String): Genre? {
        val stmt = dataSource.connection.prepareStatement(
                "SELECT id, name FROM genres WHERE name = ?"
        )
        stmt.setString(1, name)

        val queryResult = stmt.executeQuery()

        return if (queryResult.next()) {
            Genre(queryResult.getInt("id"), queryResult.getString("name"))
        } else {
            null
        }
    }

    fun updateGenre(genre: Genre) {
        val stmt = dataSource.connection.prepareStatement(
                "UPDATE genres SET name=? WHERE id = ? "
        )
        stmt.setString(1, genre.name)
        stmt.setInt(2, genre.id)
        stmt.executeUpdate()
    }

    fun deleteGenre(genre: Genre) {
        val stmt = dataSource.connection.prepareStatement(
                "DELETE FROM genres WHERE id = ?"
        )
        stmt.setInt(1, genre.id)
        stmt.executeUpdate()
    }

}