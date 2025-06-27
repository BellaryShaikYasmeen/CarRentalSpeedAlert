package com.example.carrentalspeedalert.domain

data class Rental(
    val customerId: String,
    val vehicleId: String,
    val maxSpeed: Int,
)
