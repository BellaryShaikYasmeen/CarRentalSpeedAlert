package com.example.carrentalspeedalert

import com.example.carrentalspeedalert.domain.SpeedEvent
import com.example.carrentalspeedalert.notifier.FirebaseNotifier
import com.example.carrentalspeedalert.repositary.InMemoryRentalRepository
import com.example.carrentalspeedalert.usecase.SpeedMonitorUseCase

fun main() {
    val repository = InMemoryRentalRepository()
    val notifier = FirebaseNotifier()
    val useCase = SpeedMonitorUseCase(notifier, repository)


    val events = listOf(
        SpeedEvent("R1", 105),  // Over limit
        SpeedEvent("R2", 85),   // Below limit
        SpeedEvent("R2", 100),  // Over limit
        SpeedEvent("R3", 120)   // Invalid rental ID
    )

    events.forEach { useCase.onSpeedEvent(it) }
}