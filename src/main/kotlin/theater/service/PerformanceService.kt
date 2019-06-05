package theater.service

import theater.Info
import theater.model.Role
import theater.dao.RoleDao
import theater.TheaterDataSource
import theater.dao.*
import theater.model.*
import java.sql.Date


class PerformanceService(
        private val dataSource: TheaterDataSource,
        private val performanceDao: PerformanceDao,
        private val concertTourDao: ConcertTourDao,
        private val roleDao: RoleDao,
        private val featureDao: FeatureDao,
        private val employeesDao: EmployeesDao,
        private val countryDao: CountryDao
) : Service() {

    fun createPerformance(toCreate: Performance): Int {
        return transaction(dataSource) {
            val performanceId = performanceDao.createPerformance(toCreate)
            performanceId
        }
    }

    fun createPerformances(toCreate: Iterable<Performance>): List<Int> {
        return transaction(dataSource) {

            val performanceIds = performanceDao.createPerformances(toCreate)
            performanceIds
        }
    }

    fun createConcertTour(toCreate: ConcertTour): Int {
        return transaction(dataSource) {
            val concertTourId = concertTourDao.createConcertTour(toCreate)
            concertTourId
        }
    }

    fun createConcertTours(toCreate: Iterable<ConcertTour>): List<Int> {
        return transaction(dataSource) {

            val concertTourIds = concertTourDao.createConcertTours(toCreate)
            concertTourIds
        }
    }

    fun createRole(toCreate: Role): Int {
        return transaction(dataSource) {
            val roleId = roleDao.createRole(toCreate)
            roleId
        }
    }

    fun createRoles(toCreate: Iterable<Role>): List<Int> {
        return transaction(dataSource) {

            val roleIds = roleDao.createRoles(toCreate)
            roleIds
        }
    }

    fun createFeature(toCreate: Feature): Int {
        return transaction(dataSource) {
            val featureId = featureDao.createFeature(toCreate)
            featureId
        }
    }

    fun createFeatures(toCreate: Iterable<Feature>): List<Int> {
        return transaction(dataSource) {

            val featureIds = featureDao.createFeatures(toCreate)
            featureIds
        }
    }

    fun findPerformance(id: Int): Performance? {
        return transaction(dataSource) {
            performanceDao.findPerformance(id)
        }
    }

    fun findConcertTour(id: Int): ConcertTour? {
        return transaction(dataSource) {
            concertTourDao.findConcertTour(id)
        }
    }

    fun findRole(id: Int): Role? {
        return transaction(dataSource) {
            roleDao.findRole(id)
        }
    }

    fun findFeature(id: Int): Feature? {
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

    fun deletePerformance(id: Int): Int {
        return transaction(dataSource) {
            performanceDao.deletePerformance(id)
        }
    }

    fun deleteConcertTour(id: Int): Int {
        return transaction(dataSource) {
            concertTourDao.deleteConcertTour(id)
        }
    }

    fun deleteRole(id: Int): Int {
        return transaction(dataSource) {
            roleDao.deleteRole(id)
        }
    }

    fun deleteFeature(id: Int): Int {
        return transaction(dataSource) {
            featureDao.deleteFeature(id)
        }
    }

    fun addConcertTourToPerformance(performanceId: Int, concertTourId: Int) {
        return transaction(dataSource) {
            performanceDao.addConcertTourToPerformance(performanceId, concertTourId)
        }
    }

    fun addRoleToPerformance(performanceId: Int, roleId: Int) {
        return transaction(dataSource) {
            performanceDao.addRoleToPerformance(performanceId, roleId)
        }
    }

    fun addFeatureToRole(roleId: Int, featureId: Int) {
        return transaction(dataSource) {
            roleDao.addFeatureToRole(roleId, featureId)
        }
    }

    fun getTourTroupe(spectacleId: Int, start: Date, finish: Date): String {
        val res = concertTourDao.getTourTroupe(
            employeesDao, spectacleId,
            start, finish
        )
        return res.toString()
    }

    fun getPerformanceInfo(spectacleId: Int): Info {
        return performanceDao.getPerformanceInfo(employeesDao, spectacleId, countryDao)
    }
}