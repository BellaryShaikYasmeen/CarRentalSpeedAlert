package com.example.carrentalspeedalert.domain

data class Rental(
    val rentalId: String,
    val customer: Customer,
    val maxAllowedSpeed: Int
)