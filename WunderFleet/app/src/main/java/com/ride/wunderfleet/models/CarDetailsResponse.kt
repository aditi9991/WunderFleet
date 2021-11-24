package com.ride.wunderfleet.models

import com.google.gson.annotations.SerializedName
//POJO for each car detail
data class CarDetailsResponse(
    @SerializedName("carId") var carId: Int? = 0,
    @SerializedName("title") var title: String? = "",
    @SerializedName("isClean") var isClean: Boolean? = false,
    @SerializedName("isDamaged") var isDamaged: Boolean? = false,
    @SerializedName("licencePlate") var licencePlate: String? = "",
    @SerializedName("fuelLevel") var fuelLevel: Int? = 0,
    @SerializedName("vehicleStateId") var vehicleStateId: Int? = 0,
    @SerializedName("hardwareId") var hardwareId: String? = "",
    @SerializedName("vehicleTypeId") var vehicleTypeId: Int? = 0,
    @SerializedName("pricingTime") var pricingTime: String? = "",
    @SerializedName("pricingParking") var pricingParking: String? = "",
    @SerializedName("isActivatedByHardware") var isActivatedByHardware: Boolean? = false,
    @SerializedName("locationId") var locationId: Int? = 0,
    @SerializedName("address") var address: String? = "",
    @SerializedName("zipCode") var zipCode: String? = "",
    @SerializedName("city") var city: String? = "",
    @SerializedName("lat") var lat: Double? = 0.0,
    @SerializedName("lon") var lon: Double? = 0.0,
    @SerializedName("reservationState") var reservationState: Int? = 0,
    @SerializedName("damageDescription") var damageDescription: String? = "",
    @SerializedName("vehicleTypeImageUrl") var vehicleTypeImageUrl: String? = ""
)
