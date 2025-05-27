package com.example.carrentalspeedalert

import com.example.carrentalspeedalert.domain.Customer
import com.example.carrentalspeedalert.domain.Rental
import com.example.carrentalspeedalert.domain.SpeedEvent
import com.example.carrentalspeedalert.notifier.FirebaseNotifier
import com.example.carrentalspeedalert.repositary.RentalRepository
import com.example.carrentalspeedalert.usecase.SpeedMonitorUseCase

import org.junit.Assert.*

class FakeNotifier : FirebaseNotifier() {
    val companyNotifications = mutableListOf<Pair<String, Int>>()
    val customerAlerts = mutableListOf<Pair<String, String>>()

    override fun notifyRentalCompany(rentalId: String, speed: Int) {
        companyNotifications.add(rentalId to speed)
    }

    override fun alertCustomer(rentalId: String, message: String) {
        customerAlerts.add(rentalId to message)
    }
}

fun runUnitTests() {
    val rental = Rental("R1", Customer("C1", "Alice"), 100)
    val repository = object : RentalRepository {
        override fun getRentalById(rentalId: String): Rental? = if (rentalId == "R1") rental else null
    }

    val fakeNotifier = FakeNotifier()
    val useCase = SpeedMonitorUseCase(fakeNotifier, repository)

    println("Test 1: Speed below limit")
    useCase.onSpeedEvent(SpeedEvent("R1", 90))
    assert(fakeNotifier.companyNotifications.isEmpty())
    assert(fakeNotifier.customerAlerts.isEmpty())

    println("Test 2: Speed above limit")
    useCase.onSpeedEvent(SpeedEvent("R1", 110))
    assert(fakeNotifier.companyNotifications.contains("R1" to 110))
    assert(fakeNotifier.customerAlerts.any { it.first == "R1" && it.second.contains("speeding at 110") })

    println("Test 3: Unknown rental ID")
    useCase.onSpeedEvent(SpeedEvent("R2", 120))
    assert(fakeNotifier.companyNotifications.size == 1) // still only one from above
    assert(fakeNotifier.customerAlerts.size == 1)

    println("All tests passed.")
}