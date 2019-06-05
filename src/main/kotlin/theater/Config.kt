package theater

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import theater.dao.*
import theater.service.*

@Configuration
class Config {

    @Bean
    fun db(): Db {
        return Db()
    }

    @Bean
    fun theatreDs(): TheaterDataSource {
        return TheaterDataSource(db().dataSource)
    }

    @Bean
    fun actorsDao(): ActorsDao {
        return ActorsDao(theatreDs())
    }

    @Bean
    fun authorDao(): AuthorDao {
        return AuthorDao(theatreDs())
    }

    @Bean
    fun concertTourDao(): ConcertTourDao {
        return ConcertTourDao(theatreDs())
    }

    @Bean
    fun countryDao(): CountryDao {
        return CountryDao(theatreDs())
    }

    @Bean
    fun employeesDao(): EmployeesDao {
        return EmployeesDao(theatreDs())
    }

    @Bean
    fun featureDao(): FeatureDao {
        return FeatureDao(theatreDs())
    }

    @Bean
    fun genreDao(): GenreDao {
        return GenreDao(theatreDs())
    }

    @Bean
    fun musiciansDao(): MusiciansDao {
        return MusiciansDao(theatreDs())
    }

    @Bean
    fun performanceDao(): PerformanceDao {
        return PerformanceDao(theatreDs())
    }

    @Bean
    fun producersDao(): ProducersDao {
        return ProducersDao(theatreDs())
    }

    @Bean
    fun roleDao(): RoleDao {
        return RoleDao(theatreDs())
    }

    @Bean
    fun servantsDao(): ServantsDao {
        return ServantsDao(theatreDs())
    }

    @Bean
    fun showDao(): ShowDao {
        return ShowDao(theatreDs())
    }

    @Bean
    fun spectacleDao(): SpectacleDao {
        return SpectacleDao(theatreDs())
    }

    @Bean
    fun ticketDao(): TicketDao {
        return TicketDao(theatreDs())
    }

    @Bean
    fun authorService(): AuthorService {
        return AuthorService(theatreDs(), authorDao(), countryDao())
    }

    @Bean
    fun countryService(): CountryService {
        return CountryService(theatreDs(), countryDao())
    }

    @Bean
    fun employeeService(): EmployeeService {
        return EmployeeService(employeesDao(), producersDao(), actorsDao(), musiciansDao(), servantsDao())
    }

    @Bean
    fun genreService(): GenreService {
        return GenreService(theatreDs(), genreDao())
    }

    @Bean
    fun performanceService(): PerformanceService {
        return PerformanceService(theatreDs(), performanceDao(), concertTourDao(), roleDao(), featureDao())
    }

    @Bean
    fun showService(): ShowService {
        return ShowService(theatreDs(), showDao())
    }

    @Bean
    fun spectacleService(): SpectacleService {
        return SpectacleService(theatreDs(), spectacleDao(), genreDao(), authorDao(), countryDao())
    }

    @Bean
    fun ticketService(): TicketService {
        return TicketService(theatreDs(), ticketDao())
    }
}