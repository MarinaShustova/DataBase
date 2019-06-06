package theater.service

import javax.sql.DataSource

open class Service {
    fun <T> transaction(ds: DataSource, body: () -> T): T {
        ds.connection.use {
            it.autoCommit = false
            try {
                val res = body()
                it.commit()
                return res
            } catch (e: Exception) {
                it.rollback()
                throw e
            }
        }
    }
}