package main.kotlin.theater

import theater.*

fun main() {
    val db = DataBase()
    val employeesDao = EmployeesDao(db.dataSource)
    val producersDao = ProducersDao(db.dataSource)
    val actorsDao = ActorsDao(db.dataSource)
    val musiciansDao = MusiciansDao(db.dataSource)
    val servantsDao = ServantsDao(db.dataSource)
    val service = Service(employeesDao, producersDao, actorsDao, musiciansDao, servantsDao)
    val controller = Controller(service)

    generateSequence { print("> "); readLine() }
            .takeWhile { it != "exit" }
            .map {
                try {
                    if (it.startsWith("create")) {
                        if (it.contains("producer")) {
                            controller.createProducer(it.substring("create producer".length).trim());
                        } else if (it.contains("actor")) {
                            controller.createActor(it.substring("create actor".length).trim());
                        } else if (it.contains("musician")) {
                            controller.createMusician(it.substring("create musician".length).trim());
                        } else if (it.contains("servant")) {
                            controller.createServant(it.substring("create servant".length).trim());
                        }
                        else {
                            "Unknown command: $it"
                        }
                    } else if (it.startsWith("delete")){
                        if (it.contains("producer")) {
                            controller.deleteProducer(it.substring("delete producer".length).trim());
                        } else if (it.contains("actor")) {
                            controller.deleteActor(it.substring("delete actor".length).trim());
                        } else if (it.contains("musician")) {
                            controller.deleteMusician(it.substring("delete musician".length).trim());
                        } else if (it.contains("servant")) {
                            controller.deleteServant(it.substring("delete servant".length).trim());
                        } else {
                            "Unknown command: $it"
                        }
                    } else if (it.startsWith("update")) {
                        if (it.contains("producer")) {
                            controller.updateProducer(it.substring("update producer".length).trim());
                        } else if (it.contains("actor")) {
                            controller.deleteActor(it.substring("update actor".length).trim());
                        } else if (it.contains("musician")) {
                            controller.deleteMusician(it.substring("update musician".length).trim());
                        } else if (it.contains("servant")) {
                            controller.deleteServant(it.substring("update servant".length).trim());
                        } else {
                            "Unknown command: $it"
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