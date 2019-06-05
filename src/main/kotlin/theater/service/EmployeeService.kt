package theater.service

import theater.Info
import theater.TheaterDataSource
import theater.dao.*
import theater.model.*
import javax.sql.DataSource
import theater.model.Actor
import theater.model.Musician
import theater.model.Producer
import theater.model.Servant
import java.sql.Date

class EmployeeService(
        private val dataSource: DataSource,
        private val employeesDao: EmployeesDao,
        private val producersDao: ProducersDao,
        private val actorsDao: ActorsDao,
        private val musiciansDao: MusiciansDao,
        private val servantsDao: ServantsDao) : Service() {

    fun getEmployees(): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployees()
        }
    }

    fun getActors(): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActors()
        }
    }

    fun getMusicians(): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusicians()
        }
    }

    fun getProducers(): List<Producer> {
        return transaction(dataSource) {
            producersDao.getProducers()
        }
    }

    fun getServants(): List<Servant> {
        return transaction(dataSource) {
            servantsDao.getServants()
        }
    }
    
    fun getProducerById(id: Int): Producer? {
        return transaction(dataSource) {
            producersDao.getProducerById(id)
        }
    }

    fun getProducerByName(fio: String): Producer? {
        return transaction(dataSource) {
            producersDao.getProducerByName(fio)
        }
    }

    fun createProducer(toCreate: Producer): Int {
        return transaction(dataSource) {
            val employeeId = employeesDao.createEmployee(toCreate.employee)
            toCreate.employee.id = employeeId
            producersDao.createProducer(toCreate)
        }
    }

    fun deleteProducer(id: Int) {
        return transaction(dataSource) {
            producersDao.deleteProducer(id)
        }
    }

    fun getActorById(id: Int): Actor? {
        return transaction(dataSource) {
            actorsDao.getActorById(id)
        }
    }

    fun getActorByName(fio: String): Actor? {
        return transaction(dataSource) {
            actorsDao.getActorByName(fio)
        }
    }

    fun createActor(toCreate: Actor): Int {
        return transaction(dataSource) {
            val employeeId = employeesDao.createEmployee(toCreate.employee)
            toCreate.employee.id = employeeId
            actorsDao.createActor(toCreate)
        }
    }

    fun deleteActor(id: Int) {
        return transaction(dataSource) {
            actorsDao.deleteActor(id)
        }
    }

    fun getMusicianById(id: Int): Musician? {
        return transaction(dataSource) {
            musiciansDao.getMusicianById(id)
        }
    }

    fun getMusicianByName(fio: String): Musician? {
        return transaction(dataSource) {
            musiciansDao.getMusicianByName(fio)
        }
    }

    fun createMusician(toCreate: Musician): Int {
        return transaction(dataSource) {
            val employeeId = employeesDao.createEmployee(toCreate.employee)
            toCreate.employee.id = employeeId
            musiciansDao.createMusician(toCreate)
        }
    }

    fun deleteMusician(id: Int) {
        return transaction(dataSource) {
            musiciansDao.deleteMusician(id)
        }
    }

    fun getServantById(id: Int): Servant? {
        return transaction(dataSource) {
            servantsDao.getServantById(id)
        }
    }

    fun getServantByName(fio: String): Servant? {
        return transaction(dataSource) {
            servantsDao.getServantByName(fio)
        }
    }

    fun createServant(toCreate: Servant): Int {
        return transaction(dataSource) {
            val employeeId = employeesDao.createEmployee(toCreate.employee)
            toCreate.employee.id = employeeId
            servantsDao.createServant(toCreate)
        }
    }

    fun deleteServant(id: Int) {
        return transaction(dataSource) {
            servantsDao.deleteServant(id)
        }
    }

    fun updateProducer(id: Int, keysNValues: Map<String, String>) {
        return transaction(dataSource) {
            producersDao.updateProducer(id, keysNValues)
        }
    }

    fun updateProducer(toUpdate: Producer) {
        return transaction(dataSource) {
            producersDao.updateProducer(toUpdate)
        }
    }

    fun updateActor(id: Int, keysNValues: Map<String, String>) {
        return transaction(dataSource) {
            actorsDao.updateActor(id, keysNValues)
        }

    }

    fun updateActor(toUpdate: Actor) {
        return transaction(dataSource) {
            actorsDao.updateActor(toUpdate)
        }

    }
    
    fun updateMusician(id: Int, keysNValues: Map<String, String>) {
        return transaction(dataSource) {
            musiciansDao.updateMusician(id, keysNValues)
        }
    }

    fun updateMusician(toUpdate: Musician) {
        return transaction(dataSource) {
            musiciansDao.updateMusician(toUpdate)
        }
    }


    fun updateServant(toUpdate: Servant) {
        return transaction(dataSource) {
            servantsDao.updateServant(toUpdate)
        }
    }


    fun updateServant(id: Int, keysNValues: Map<String, String>) {
        return transaction(dataSource) {
            servantsDao.updateServant(id, keysNValues)
        }
    }


    fun getEmployeeById(id: Int): Employee? {
        return transaction(dataSource) {
            employeesDao.getEmployeeById(id)
        }
    }

    fun getEmployeeByName(fio: String): Employee? {
        return transaction(dataSource) {
            employeesDao.getEmployeeByName(fio)
        }
    }


    fun getEmployeesBySex(sex: String): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesBySex(sex)
        }
    }

    fun getEmployeesByExperience(years: Int): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesByExperience(years)
        }
    }

    fun getEmployeesByBirthDate(birth: Date): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesByBirthDate(birth)
        }
    }

    fun getEmployeesByAge(age: Int): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesByAge(age)
        }
    }

    fun getEmployeesByChildrenAmount(count: Int): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesByChildrenAmount(count)
        }
    }

    fun getEmployeesBySalary(salary: Int): List<Employee> {
        return transaction(dataSource) {
            employeesDao.getEmployeesBySalary(salary)
        }
    }

    fun getMusiciansBySex(sex: String): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansBySex(sex)
        }
    }

    fun getMusiciansByExperience(years: Int): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansByExperience(years)
        }
    }

    fun getMusiciansByBirthDate(birth: Date): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansByBirthDate(birth)
        }
    }

    fun getMusiciansByAge(age: Int): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansByAge(age)
        }
    }

    fun getMusiciansByChildrenAmount(count: Int): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansByChildrenAmount(count)
        }
    }

    fun getMusiciansBySalary(salary: Int): List<Musician> {
        return transaction(dataSource) {
            musiciansDao.getMusiciansBySalary(salary)
        }
    }


    fun getActorsBySex(sex: String): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsBySex(sex)
        }
    }

    fun getActorsByExperience(years: Int): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsByExperience(years)
        }
    }

    fun getActorsByBirthDate(birth: Date): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsByBirthDate(birth)
        }
    }

    fun getActorsByAge(age: Int): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsByAge(age)
        }
    }

    fun getActorsByChildrenAmount(count: Int): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsByChildrenAmount(count)
        }
    }

    fun getActorsBySalary(salary: Int): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsBySalary(salary)
        }
    }

    fun getActorsRoles(actorId: Int): List<Role> {
        return transaction(dataSource) {
            actorsDao.getActorsRoles(actorId)
        }
    }

    fun getActorsRolesByGenre(actorId: Int, genre: String): List<Role> {
        return transaction(dataSource) {
            actorsDao.getActorsRolesByGenre(actorId, genre)
        }
    }

    fun getActorsRolesByAgeCategory(actorId: Int, age: Int): List<Role> {
        return transaction(dataSource) {
            actorsDao.getActorsRolesByAgeCategory(actorId, age)
        }
    }

    fun getActorsRolesByProducer(actorId: Int, producerId: Int): List<Role> {
        return transaction(dataSource) {
            actorsDao.getActorsRolesByProducer(actorId, producerId)
        }
    }

    fun getActorsRolesByPeriod(actorId: Int, periodStart: Date, periodEnd: Date): List<Role> {
        return transaction(dataSource) {
            actorsDao.getActorsRolesByPeriod(actorId, periodStart, periodEnd)
        }

    }

    //functions for selections

    fun getActorsWithRanks(): List<Actor> {
        return transaction(dataSource) {
            actorsDao.getActorsWithRanks(employeesDao)
        }
    }

    fun getActorsWithRanksSex(sex: String): List<Actor> {
        return actorsDao.getActorsWithRanksSex(employeesDao, sex)
    }

    fun getActorsWithRanksAge(age: Int): List<Actor> {
        return actorsDao.getActorsWithRanksAge(employeesDao, age)
    }

    fun getActorsWithRanksContests(contests: List<String>): List<Actor> {
        return actorsDao.getActorsWithRanksContests(employeesDao, contests)
    }

    fun getTourTroupe(spectacleId: Int, start: Date, finish: Date): Pair<List<Actor>, List<Producer>> {
        val res = actorsDao.getTourTroupe(
                employeesDao, spectacleId,
                start, finish
        )
        return res
    }

    fun getPerformanceInfo(spectacleId: Int, countryDao: CountryDao): Info {
        return actorsDao.getPerformanceInfo(employeesDao, spectacleId, countryDao)
    }

}