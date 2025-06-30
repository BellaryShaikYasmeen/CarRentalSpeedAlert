package com.example.carrentalspeedalert

interface NotificationStrategy {
    fun notify(customerId: String, speed: Int)
}