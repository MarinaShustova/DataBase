package theater.model

data class Ticket(val id: Int,
                  val row: Int,
                  val seat: Int,
                  val price: Int,
                  val presence: Boolean,
                  val previously: Boolean,
                  val showId: Int?)