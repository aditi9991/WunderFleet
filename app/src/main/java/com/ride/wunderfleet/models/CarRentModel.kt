package com.ride.wunderfleet.models

import com.google.gson.annotations.SerializedName

data class CarRentModel(
    @SerializedName("carId")
    var carId: Int? = 0
)