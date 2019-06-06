package theater

import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource

class DataBase {
    val dataSource = PGSimpleDataSource().apply {
        serverName = "localhost"
        portNumber = 5432
        databaseName = "theater"
        user = "postgres"
        password = "mysecretpassword"
    }

    init {
        val flyway = Flyway
                .configure()
                .dataSource(dataSource)
                .load()
//        flyway.baseline()
        flyway.migrate()
    }

}