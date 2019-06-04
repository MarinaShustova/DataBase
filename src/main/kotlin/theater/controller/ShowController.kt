package theater.controller

import org.springframework.web.bind.annotation.RestController
import theater.model.Show
import theater.service.ShowService
import java.sql.Date
import java.sql.Timestamp

@RestController
class ShowController(private val showService: ShowService) {

    fun createShow(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 2) {
            return "2 arg expected: $args"
        }

        val show = Show(-1, Timestamp.valueOf(args[0]), args[1].toBoolean())
        val showId = showService.createShow(show)
        return "Created show: $showId, date = ${show.date}, is premiere = ${show.premiere}"
    }

    fun updateShow(argsStr: String): String {
        val args = argsStr.split(",")
                .map { it.trim() }
        if (argsStr.isEmpty() || args.size != 3) {
            return "3 arg expected: $args"
        }

        val show = Show(args[0].toInt(), Timestamp.valueOf(args[1]), args[2].toBoolean())
        showService.updateShow(show)
        return "Show updated"
    }

    fun getShow(argsStr: String): String {
        try {
            val id = argsStr.toInt()
            val show = showService.getShow(id) ?: return "Can't find show with id $id"
            return "Show: date = ${show.date}, is premiere = ${show.premiere}"
        } catch (ex: NumberFormatException) {
            return "argument should be integer"
        }
    }

    fun deleteShow(argsStr: String): String {
        showService.deleteShow(argsStr.toInt())
        return "Deleted show argsStr"
    }
}