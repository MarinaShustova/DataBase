package theater.dao

import java.sql.Statement
import javax.sql.DataSource
import theater.model.*

class RoleDao(private val dataSource: DataSource) {

    fun createRole(toCreate: Role): Long {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO roles (name) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.name)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun createRoles(toCreate: Iterable<Role>): List<Long> {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO roles (name) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS

        )
        for (role in toCreate) {
            stmt.setString(1, role.name)
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

    fun findRole(id: Long): Role? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "r.id rid, r.name "+
                    "FROM roles AS r " +
                    "WHERE r.id = ?"
        )
        stmt.setLong(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Role(
                rs.getLong("rid"), rs.getString("name"))
        } else {
            null
        }
    }

    fun deleteRole(id: Long): Long {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM roles WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setLong(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getLong(1)
    }

    fun updateRole(role: Role) {
        val stmt = dataSource.connection.prepareStatement("UPDATE roles SET name = ? WHERE id = ?")
        stmt.setString(1, role.name)
        stmt.setLong(2, role.id!!)
        stmt.executeUpdate()
    }

    fun getRoles(page: Page): List<Role> {
        val theQuery = "SELECT id, name FROM roles ORDER BY name LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * page.num)

        val res = ArrayList<Role>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(Role(
                rs.getLong("id"), rs.getString("name"))
            )
        }
        return res
    }

    fun addFeatureToRole(roleId: Long, featureId: Long) {
        val stmt =
            dataSource.connection.prepareStatement("INSERT INTO requires (role_id, feature_id) VALUES (?,?)")
        stmt.setLong(1, roleId)
        stmt.setLong(2, featureId)
        stmt.executeUpdate()
    }

}
