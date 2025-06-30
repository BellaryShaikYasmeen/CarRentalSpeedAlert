package com.example.carrentalspeedalert.domain
enum class ChannelType {
    FIREBASE,
    AWS
}

data class Rental(
    val customerId: String,
    val vehicleId: String,
    val maxSpeed: Int,
    var channelType: ChannelType // <- Add this line
)
