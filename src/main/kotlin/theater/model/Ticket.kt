package theater.model

data class Ticket(val id: Int,
                  val row: Int,
                  val seat: Int,
                  val price: Int,
                  var presence: Boolean,
                  var previously: Boolean,
                  val showId: Int?)