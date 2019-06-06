package theater.dao

import java.sql.Statement
import javax.sql.DataSource
import theater.model.*
import theater.model.data.RoleData

class RoleDao(private val dataSource: DataSource) {

    fun createRole(toCreate: Role): Int {
        val stmt = dataSource.connection.prepareStatement(
            "INSERT INTO roles (name) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setString(1, toCreate.name)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun createRoles(toCreate: Iterable<Role>): List<Int> {
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
        val res = ArrayList<Int>()
        while (gk.next()) {
            res += gk.getInt(1)
        }

        return res
    }

    fun findRole(id: Int): Role? {
        val stmt = dataSource.connection.prepareStatement(
            "SELECT " +
                    "r.id rid, r.name " +
                    "FROM roles AS r " +
                    "WHERE r.id = ?"
        )
        stmt.setInt(1, id)
        val rs = stmt.executeQuery()
        return if (rs.next()) {
            Role(
                rs.getInt("rid"), rs.getString("name")
            )
        } else {
            null
        }
    }

    fun deleteRole(id: Int): Int {
        val stmt = dataSource.connection.prepareStatement(
            "DELETE FROM roles WHERE id = ?",
            Statement.RETURN_GENERATED_KEYS
        )
        stmt.setInt(1, id)
        stmt.executeUpdate()
        val gk = stmt.generatedKeys
        gk.next()

        return gk.getInt(1)
    }

    fun updateRole(role: Role) {
        val stmt = dataSource.connection.prepareStatement("UPDATE roles SET name = ? WHERE id = ?")
        stmt.setString(1, role.name)
        stmt.setInt(2, role.id!!)
        stmt.executeUpdate()
    }

    fun getRoles(page: Page): ArrayList<RoleData> {
        val theQuery = "SELECT id, name FROM roles ORDER BY name LIMIT ? OFFSET ?"
        val conn = dataSource.connection
        val stmt = conn.prepareStatement(theQuery)
        stmt.setInt(1, page.size)
        stmt.setInt(2, page.size * (page.num - 1))

        val res = ArrayList<RoleData>()
        val rs = stmt.executeQuery()
        while (rs.next()) {
            res.add(
                RoleData(
                    Role(
                        rs.getInt("id"), rs.getString("name")
                    )
                )
            )
        }
        return res
    }

    fun addFeatureToRole(roleId: Int, featureId: Int) {
        val stmt =
            dataSource.connection.prepareStatement("INSERT INTO roles_features (role_id, feature_id) VALUES (?,?)")
        stmt.setInt(1, roleId)
        stmt.setInt(2, featureId)
        stmt.executeUpdate()
    }


}
