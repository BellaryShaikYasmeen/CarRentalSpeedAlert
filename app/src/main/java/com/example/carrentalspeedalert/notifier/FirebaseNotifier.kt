package com.example.carrentalspeedalert.notifier
// --- FIREBASE NOTIFIER ---
class FirebaseNotifier {
    fun notifyRentalCompany(rentalId: String, speed: Int) {
        //  Firebase Cloud Messaging (FCM) API call
        // Example:
        // POST to https://fcm.googleapis.com/fcm/send
        // Body: {
        //   "to": "/topics/fleet_notifications",
        //   "notification": {
        //     "title": "Speed Violation",
        //     "body": "Rental $rentalId exceeded speed: $speed km/h"
        //   }
        // }
        println("[Firebase] Notify rental company: Rental $rentalId exceeded speed: $speed km/h")
    }

    fun alertCustomer(rentalId: String, message: String) {
        //  Replace with FCM push to customer's device token
        // Example: send to token stored in Firestore under /tokens/{customerId}
        println("[Firebase] Alert to customer (Rental $rentalId): $message")
    }
}
