package com.example.carrentalspeedalert.repositary

import com.example.carrentalspeedalert.domain.ChannelType
import com.example.carrentalspeedalert.domain.Rental

class RentalRepository {
    private val rentals = mutableMapOf<String, Rental>()

    fun addOrUpdateRental(rental: Rental) {
        rentals[rental.customerId] = rental
    }

    fun getRental(customerId: String): Rental? = rentals[customerId]
    fun switchChannel(customerId: String, newChannel: ChannelType): Boolean {
        val rental = rentals[customerId]
        return if (rental != null) {
            rental.channelType = newChannel
            true
        } else {
            false
        }
    }

}
