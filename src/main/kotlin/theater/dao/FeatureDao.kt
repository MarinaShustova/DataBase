package theater.dao

import java.sql.Statement
import javax.sql.DataSource
import theater.model.*

class FeatureDao(private val dataSource: DataSource) {

    fun createFeature(toCreate: Feature): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO features (name, value) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.name)
        stmt.setString(2, toCreate.value)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun createFeatures(toCreate: Iterable<Feature>): List<Long> {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO features (name, value) VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS

        )
        for (feature in toCreate) {
            stmt.setString(1, feature.name)
            stmt.setString(2, feature.value)
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

    fun findFeature(id: Long): Feature? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "f.id fid, f.name, f.value "+
                    "FROM features AS f " +
                    "WHERE f.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Feature(
                rs.getLong("fid"), rs.getString("name"), rs.getString("value"))
        } else {
            null
        }
    }

    fun deleteFeature(id: Long): Long {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM features WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun updateFeature(feature: Feature) {
        val stmt = dataSource.connection.prepareStatement("UPDATE features SET name = ?, value = ? WHERE id = ?")
        stmt.setString(1, feature.name)
        stmt.setString(2, feature.value)
        stmt.setLong(3, feature.id!!)
        stmt.executeUpdate()
    }

    fun getFeatures(page: Page): List<Feature> {
        val theQuery = "SELECT id, name, value FROM features ORDER BY name LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * page.num)

        val res = ArrayList<Feature>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                Feature(
                rs.getLong("id"), rs.getString("name"), rs.getString("value"))
            )
        }
        return res
    }

}
