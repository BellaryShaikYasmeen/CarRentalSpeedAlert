package com.example.carrentalspeedalert.usecase

import com.example.carrentalspeedalert.NotificationDispatcher.NotificationDispatcher
import com.example.carrentalspeedalert.repositary.RentalRepository

class SpeedMonitor(
    private val repo: RentalRepository,
    private val dispatcher: NotificationDispatcher
) {
    fun checkSpeed(customerId: String, currentSpeed: Int) {
        val rental = repo.getRental(customerId) ?: return
        if (currentSpeed > rental.maxSpeed) {
            println("⚠️ Warning: User exceeded speed limit of ${rental.maxSpeed} km/h.")
            val strategy = dispatcher.getStrategy(rental.channelType)
            strategy.notify(customerId, currentSpeed)
        } else {
            println("✅ Speed is within the limit for $customerId.")
        }
    }
}