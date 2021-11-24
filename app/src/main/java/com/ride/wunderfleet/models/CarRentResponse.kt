package com.ride.wunderfleet.models
//POJO for response on car rent api call
data class CarRentResponse(
    val carId: Int,
    val cost: Int,
    val damageDescription: String,
    val drivenDistance: Int,
    val endTime: Int,
    val fuelCardPin: String,
    val isParkModeEnabled: Boolean,
    val licencePlate: String,
    val reservationId: Int,
    val startAddress: String,
    val startTime: Int,
    val userId: Int
)