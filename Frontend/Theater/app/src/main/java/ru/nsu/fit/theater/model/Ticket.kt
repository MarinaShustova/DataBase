package ru.nsu.fit.theater.model

data class Ticket(var id: Int,
                  val row: Int,
                  val seat: Int,
                  val price: Int,
                  val presence: Boolean,
                  val previously: Boolean)