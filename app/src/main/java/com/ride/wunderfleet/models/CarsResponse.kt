package com.ride.wunderfleet.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CarsResponse(
    @SerializedName("carId") var carId: Int? = 0,
    @SerializedName("title") var title: String? = "",
    @SerializedName("lat") var lat: Double = 0.0,
    @SerializedName("lon") var lon: Double = 0.0,
    @SerializedName("licencePlate") var licencePlate: String? = "",
    @SerializedName("fuelLevel") var fuelLevel: Int? = 0,
    @SerializedName("vehicleStateId") var vehicleStateId: Int? = 0,
    @SerializedName("vehicleTypeId") var vehicleTypeId: Int? = 0,
    @SerializedName("pricingTime") var pricingTime: String? = "",
    @SerializedName("pricingParking") var pricingParking: String? = "",
    @SerializedName("reservationState") var reservationState: Int? = 0,
    @SerializedName("isClean") var isClean: Boolean? = false,
    @SerializedName("isDamaged") var isDamaged: Boolean? = false,
    @SerializedName("distance") var distance: String? = "",
    @SerializedName("address") var address: String? = "",
    @SerializedName("zipCode") var zipCode: String? = "",
    @SerializedName("city") var city: String? = "",
    @SerializedName("locationId") var locationId: Int? = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
    }

    companion object CREATOR : Parcelable.Creator<CarsResponse> {
        override fun createFromParcel(parcel: Parcel): CarsResponse {
            return CarsResponse(parcel)
        }

        override fun newArray(size: Int): Array<CarsResponse?> {
            return arrayOfNulls(size)
        }
    }
}
