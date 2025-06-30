package com.example.carrentalspeedalert

import com.example.carrentalspeedalert.NotificationDispatcher.NotificationDispatcher
import com.example.carrentalspeedalert.domain.ChannelType
import com.example.carrentalspeedalert.domain.Rental
import com.example.carrentalspeedalert.repositary.RentalRepository
import com.example.carrentalspeedalert.usecase.SpeedMonitor


fun main() {
    val repo = RentalRepository()
    val dispatcher = NotificationDispatcher()
    val monitor = SpeedMonitor(repo, dispatcher)

    val rental1 = Rental("cust001", "vehA", 80, ChannelType.FIREBASE)
    val rental2 = Rental("cust002", "vehB", 90, ChannelType.AWS)

    repo.addOrUpdateRental(rental1)
    repo.addOrUpdateRental(rental2)

    println("\n Checking speeds:")
    monitor.checkSpeed("cust001", 85) // Firebase alert
    monitor.checkSpeed("cust002", 95) // AWS alert
    monitor.checkSpeed("cust001", 70) // No alert
    val updated = repo.switchChannel("cust001", ChannelType.AWS)
    if (updated) {
        println("Channel switched for cust001 to AWS.")
    } else {
        println("No such customer found.")
    }


}