package com.example.carrentalspeedalert

class FirebaseNotification : NotificationStrategy {
    override fun notify(customerId: String, speed: Int) {
        println("📲 Firebase: Speed alert for customer $customerId at $speed km/h")
    }
}