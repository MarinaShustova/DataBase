package theater

import org.flywaydb.core.Flyway
import org.postgresql.ds.PGSimpleDataSource

class Db {

    val dataSource = PGSimpleDataSource().apply {
        serverName = "localhost"
        portNumber = 5432
        databaseName = "theater"
        user = "postgres"
        password = "secret"
    }

    init {

        val flyway = Flyway
                .configure()
                .dataSource(dataSource)
                .load()

        // Start the migration
        flyway.repair()
        flyway.migrate()
    }
}