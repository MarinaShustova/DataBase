package theater

class Service(private val employeesDao: EmployeesDao, private val producersDao: ProducersDao,
              private val actorsDao: ActorsDao, private val musiciansDao: MusiciansDao,
              private val servantsDao: ServantsDao) {

    fun createProducer(toCreate: Producer): Long {
        val employeeId = employeesDao.createEmployee(toCreate.employee)
        toCreate.employee.id = employeeId
        return producersDao.createProducer(toCreate)
    }

    fun deleteProducer(id: Long) {
        producersDao.deleteProducer(id)
    }

    fun createActor(toCreate: Actor): Long {
        val employeeId = employeesDao.createEmployee(toCreate.employee)
        toCreate.employee.id = employeeId
        return actorsDao.createActor(toCreate)
    }

    fun deleteActor(id: Long) {
        actorsDao.deleteActor(id)
    }

    fun createMusician(toCreate: Musician): Long {
        val employeeId = employeesDao.createEmployee(toCreate.employee)
        toCreate.employee.id = employeeId
        return musiciansDao.createMusician(toCreate)
    }

    fun deleteMusician(id: Long) {
        musiciansDao.deleteMusician(id)
    }

    fun createServant(toCreate: Servant): Long {
        val employeeId = employeesDao.createEmployee(toCreate.employee)
        toCreate.employee.id = employeeId
        return servantsDao.createServant(toCreate)
    }

    fun deleteServant(id: Long) {
        servantsDao.deleteServant(id)
    }

    fun updateProducer(id: Long, keysNValues: Map<String, String>) {
        producersDao.updateProducer(id, keysNValues)
    }

    fun updateActor(id: Long, keysNValues: Map<String, String>) {
        actorsDao.updateActor(id, keysNValues)
    }

    fun updateMusician(id: Long, keysNValues: Map<String, String>) {
        musiciansDao.updateMusician(id, keysNValues)
    }
    fun updateServant(id: Long, keysNValues: Map<String, String>) {
        servantsDao.updateServant(id, keysNValues)
    }
}