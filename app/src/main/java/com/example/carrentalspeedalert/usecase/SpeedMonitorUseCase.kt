package com.example.carrentalspeedalert.usecase

import com.example.carrentalspeedalert.domain.SpeedEvent
import com.example.carrentalspeedalert.notifier.FirebaseNotifier
import com.example.carrentalspeedalert.repositary.RentalRepository

// --- SPEED MONITOR USE CASE ---
class SpeedMonitorUseCase(
    private val firebaseNotifier: FirebaseNotifier,
    private val rentalRepository: RentalRepository
) {
    fun onSpeedEvent(event: SpeedEvent) {
        val rental = rentalRepository.getRentalById(event.rentalId)
        if (rental == null) {
            println("Rental ID ${event.rentalId} not found.")
            return
        }

        if (event.speed > rental.maxAllowedSpeed) {
            firebaseNotifier.notifyRentalCompany(rental.rentalId, event.speed)
            firebaseNotifier.alertCustomer(
                rental.rentalId,
                "You are speeding at ${event.speed} km/h. Limit: ${rental.maxAllowedSpeed} km/h"
            )
        } else {
            println("Speed is within the allowed limit for rental ${rental.rentalId}.")
        }
    }
}