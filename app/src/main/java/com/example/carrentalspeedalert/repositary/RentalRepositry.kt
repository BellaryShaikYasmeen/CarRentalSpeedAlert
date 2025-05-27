package com.example.carrentalspeedalert.repositary

import com.example.carrentalspeedalert.domain.Customer
import com.example.carrentalspeedalert.domain.Rental

interface RentalRepository {
    fun getRentalById(rentalId: String): Rental?

}
class InMemoryRentalRepository : RentalRepository {
    private val rentals = listOf(
        Rental("R1", Customer("C1", "Alice"), 100),
        Rental("R2", Customer("C2", "Bob"), 90)
    )

    override fun getRentalById(rentalId: String): Rental? {
        return rentals.find { it.rentalId == rentalId }
    }
}
