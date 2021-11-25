package com.ride.wunderfleet.viewmodel

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ride.wunderfleet.AppApplicationClass
import com.ride.wunderfleet.CarDetailsManager
import com.ride.wunderfleet.models.CarDetailsResponse
import com.ride.wunderfleet.models.CarRentModel
import com.ride.wunderfleet.models.CarRentResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class CarDetailsViewModel : ViewModel() {

    private val manager = CarDetailsManager(CompositeDisposable())
    var imgUrl = ObservableField<String>("")
    var title = ObservableField<String>("")
    var address = ObservableField<String>("")
    var damageDescription = ObservableField<String>("")
    var licencePlate = ObservableField<String>("")

    fun getCarDetails(
        carId: String,
        success: Consumer<CarDetailsResponse>,
        error: Consumer<Throwable>
    ) {
        manager.getCarDetails(carId, { response ->
            imgUrl.set(response.vehicleTypeImageUrl)
            title.set("TITLE : ${response.title}")
            address.set("ADDRESS : ${response.address + ", " + response.city + ", " + response.zipCode}")
            damageDescription.set("DAMAGE DESCRIPTION: ${response.damageDescription}")
            licencePlate.set("LICENCE PLATE: ${response.licencePlate}")
            success.accept(response)
        }, { er ->
            error.accept(er)
        })
    }

    fun rentCar(
        carrentModel: CarRentModel,
        success: Consumer<CarRentResponse>,
        error: Consumer<Throwable>
    ) {
        manager.rentCar(carrentModel, { response ->
            Toast.makeText(AppApplicationClass.context, "Booking is success", Toast.LENGTH_LONG).show()
            success.accept(response)
        }, { err ->
            Toast.makeText(AppApplicationClass.context, "Booking is failed! Please try after some time", Toast.LENGTH_LONG).show()
            error.accept(err)
        })
    }
}