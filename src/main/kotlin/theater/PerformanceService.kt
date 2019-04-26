package theater


class Service(
    private val dataSource: TheaterDataSource,
    private val performanceDao: PerformanceDao,
    private val concertTourDao: ConcertTourDao,
    private val roleDao: RoleDao,
    private val featureDao: FeatureDao
) {

    fun createPerformance(toCreate: Performance): Long {
        return transaction(dataSource) {
            val performanceId = performanceDao.createPerformance(toCreate)
            performanceId
        }
    }

    fun createPerformances(toCreate: Iterable<Performance>): List<Long> {
        return transaction(dataSource) {

            val performanceIds = performanceDao.createPerformances(toCreate)
            performanceIds
        }
    }

    fun createConcertTour(toCreate: ConcertTour): Long {
        return transaction(dataSource) {
            val concertTourId = concertTourDao.createConcertTour(toCreate)
            concertTourId
        }
    }

    fun createConcertTours(toCreate: Iterable<ConcertTour>): List<Long> {
        return transaction(dataSource) {

            val concertTourIds = concertTourDao.createConcertTours(toCreate)
            concertTourIds
        }
    }

    fun createRole(toCreate: Role): Long {
        return transaction(dataSource) {
            val roleId = roleDao.createRole(toCreate)
            roleId
        }
    }

    fun createRoles(toCreate: Iterable<Role>): List<Long> {
        return transaction(dataSource) {

            val roleIds = roleDao.createRoles(toCreate)
            roleIds
        }
    }

    fun createFeature(toCreate: Feature): Long {
        return transaction(dataSource) {
            val featureId = featureDao.createFeature(toCreate)
            featureId
        }
    }

    fun createFeatures(toCreate: Iterable<Feature>): List<Long> {
        return transaction(dataSource) {

            val featureIds = featureDao.createFeatures(toCreate)
            featureIds
        }
    }

    fun findPerformance(id: Long): Performance? {
        return transaction(dataSource) {
            performanceDao.findPerformance(id)
        }
    }

    fun findConcertTour(id: Long): ConcertTour? {
        return transaction(dataSource) {
            concertTourDao.findConcertTour(id)
        }
    }

    fun findRole(id: Long): Role? {
        return transaction(dataSource) {
            roleDao.findRole(id)
        }
    }

    fun findFeature(id: Long): Feature? {
        return transaction(dataSource) {
            featureDao.findFeature(id)
        }
    }

    fun getPerformances(page: Page): List<Performance> {
        return transaction(dataSource) {
            val res = performanceDao.getPerformances(page)
            res.map { it.season }.forEach({}) //?
            res
        }
    }

    fun getConcertTours(page: Page): List<ConcertTour> {
        return transaction(dataSource) {
            val res = concertTourDao.getConcertTours(page)
            res.map { it.city }.forEach({}) //?
            res
        }
    }

    fun getRoles(page: Page): List<Role> {
        return transaction(dataSource) {
            val res = roleDao.getRoles(page)
            //res.map { it.season }.forEach({}) //?
            res
        }
    }

    fun getFeatures(page: Page): List<Feature> {
        return transaction(dataSource) {
            val res = featureDao.getFeatures(page)
            //res.map { it.season }.forEach({}) //?
            res
        }
    }

    fun updatePerformance(performance: Performance) {
        return transaction(dataSource) {
            performanceDao.updatePerformance(performance)
        }
    }

    fun updateConcertTour(concertTour: ConcertTour) {
        return transaction(dataSource) {
            concertTourDao.updateConcertTour(concertTour)
        }
    }

    fun updateRole(role: Role) {
        return transaction(dataSource) {
            roleDao.updateRole(role)
        }
    }

    fun updateFeature(feature: Feature) {
        return transaction(dataSource) {
            featureDao.updateFeature(feature)
        }
    }

    fun deletePerformance(id: Long): Long {
        return transaction(dataSource) {
            performanceDao.deletePerformance(id)
        }
    }

    fun deleteConcertTour(id: Long): Long {
        return transaction(dataSource) {
            concertTourDao.deleteConcertTour(id)
        }
    }

    fun deleteRole(id: Long): Long {
        return transaction(dataSource) {
            roleDao.deleteRole(id)
        }
    }

    fun deleteFeature(id: Long): Long {
        return transaction(dataSource) {
            featureDao.deleteFeature(id)
        }
    }

    fun addConcertTourToPerformance(performanceId: Long, concertTourId: Long) {
        return transaction(dataSource) {
            performanceDao.addConcertTourToPerformance(performanceId, concertTourId)
        }
    }

    fun addRoleToPerformance(performanceId: Long, roleId: Long) {
        return transaction(dataSource) {
            performanceDao.addRoleToPerformance(performanceId, roleId)
        }
    }

    fun addFeatureToRole(roleId: Long, featureId: Long) {
        return transaction(dataSource) {
            roleDao.addFeatureToRole(roleId, featureId)
        }
    }
}

fun <T> transaction(ds: TheaterDataSource, body: () -> T): T {
    ds.realGetConnection().use {
        it.autoCommit = false
        ds.connection = it
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