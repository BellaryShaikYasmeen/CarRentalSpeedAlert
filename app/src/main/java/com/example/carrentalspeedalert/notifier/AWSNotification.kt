package com.example.carrentalspeedalert

class AWSNotification : NotificationStrategy {
    override fun notify(customerId: String, speed: Int) {
        println("📡 AWS: Speed alert for customer $customerId at $speed km/h")
    }
}