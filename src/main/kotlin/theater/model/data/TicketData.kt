package theater.model.data

data class TicketData(val row: Int,
                      val seat: Int,
                      val price: Int,
                      val presence: Boolean,
                      val previously: Boolean,
                      val showId: Int?)