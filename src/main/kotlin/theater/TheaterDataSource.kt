package theater

import java.sql.Connection
import javax.sql.DataSource


class TheaterDataSource(private val delegate: DataSource) : DataSource by delegate {

   private val conn = ThreadLocal<Connection>()

    private fun realGetConnection(): Connection {
        return delegate.connection
    }

    fun setConnection(c: Connection) {
        conn.set(c)
    }

    override fun getConnection(): Connection {
        return if (conn.get() != null) {
            conn.get()
        } else {
            realGetConnection()
        }
    }

    override fun getConnection(username: String?, password: String?): Connection {
        return if (conn.get() != null) {
            conn.get()
        } else {
            realGetConnection()
        }
    }
}