package ru.nsu.fit.theater.control

interface IController {
    enum class Type {
        ACTORS,
        AUTHORS,
        COUNTRIES,
        EMPLOYEES,
            FEATURES,
        GENRES,
            MUSICIANS,
            PERFORMANCES,
            PRODUCERS,
            ROLES,
            SERVANTS,
        SHOWS,
        SPECTACLES,
        TICKETS,
            TOURS
    }
}