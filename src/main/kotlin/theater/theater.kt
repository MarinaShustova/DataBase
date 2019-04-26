package theater


fun main() {
    val db = Db()
    val theaterDs = TheaterDataSource(db.dataSource)
    val performanceDao = PerformanceDao(theaterDs)
    val concertTourDao = ConcertTourDao(theaterDs)
    val roleDao = RoleDao(theaterDs)
    val featureDao = FeatureDao(theaterDs)
    val controller = PerformanceController(Service(theaterDs, performanceDao, concertTourDao, roleDao, featureDao))
    generateSequence { print("> "); readLine() }
        .takeWhile { it != "exit" }
        .map {
            try {
                if (it.startsWith("create")) {
                    if (it.contains("performance")) {
                        controller.createPerformance(it.substring("create performance".length).trim())
                    } else if (it.contains("performances")) {
                        controller.createPerformances(it.substring("create performances".length).trim())
                    } else if (it.contains("concert tour")) {
                        controller.createConcertTour(it.substring("create concert tour".length).trim())
                    } else if (it.contains("concert tours")) {
                        controller.createConcertTours(it.substring("create concert tours".length).trim())
                    } else if (it.contains("role")) {
                        controller.createRole(it.substring("create role".length).trim())
                    } else if (it.contains("roles")) {
                        controller.createRoles(it.substring("create roles".length).trim())
                    } else if (it.contains("feature")) {
                        controller.createFeature(it.substring("create feature".length).trim())
                    } else if (it.contains("features")) {
                        controller.createFeatures(it.substring("create features".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("get")) {
                    if (it.contains("performances")) {
                        controller.getPerformances(it.substring("get performances".length).trim())
                    } else if (it.contains("concert tours")) {
                        controller.getConcertTours(it.substring("get concert tours".length).trim())
                    } else if (it.contains("roles")) {
                        controller.getRoles(it.substring("get roles".length).trim())
                    } else if (it.contains("features")) {
                        controller.getFeatures(it.substring("get features".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("update")) {
                    if (it.contains("performance")) {
                        controller.updatePerformance(it.substring("update performance".length).trim())
                    } else if (it.contains("concert tour")) {
                        controller.updateConcertTour(it.substring("update concert tour".length).trim())
                    } else if (it.contains("role")) {
                        controller.updateRole(it.substring("update role".length).trim())
                    } else if (it.contains("feature")) {
                        controller.updateFeature(it.substring("update feature".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("delete")) {
                    if (it.contains("performance")) {
                        controller.deletePerformance(it.substring("delete performance".length).trim())
                    } else if (it.contains("concert tour")) {
                        controller.deleteConcertTour(it.substring("delete concert tours".length).trim())
                    } else if (it.contains("role")) {
                        controller.deleteRole(it.substring("delete role".length).trim())
                    } else if (it.contains("feature")) {
                        controller.deleteFeature(it.substring("delete feature".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else if (it.startsWith("link")){
                    if (it.contains("performance") && it.contains("concert tour")) {
                        controller.addConcertTourToPerformance(it.substring("link performance with concert tour".length).trim())
                    } else if (it.contains("role") && it.contains("performance")) {
                        controller.addRoleToPerformance(it.substring("link performance with role".length).trim())
                    } else if (it.contains("feature") && it.contains("role")) {
                        controller.addFeatureToRole(it.substring("link role with feature".length).trim())
                    } else {
                        "Unknown command"
                    }
                }
                else {
                    "Unknown command: $it"
                }
            } catch (e: Exception) {
                println("Error has been occured: $e")
                e.printStackTrace()
            }
        }.forEach { println(it) }
}