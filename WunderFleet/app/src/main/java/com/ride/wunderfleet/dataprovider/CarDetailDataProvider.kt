package com.ride.wunderfleet.dataprovider

import com.ride.wunderfleet.models.CarDetailsResponse
import com.ride.wunderfleet.models.CarRentResponse
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
//Interface for Network calls declaration
interface CarDetailDataProvider {

    fun getCarDetails(
        carId: String,
        success: Consumer<CarDetailsResponse>,
        error: Consumer<Throwable>
    ): Disposable

    fun rentCar(
        rencarRentModel: CarDetailsViewModel.carRentModel,
        success: Consumer<CarRentResponse>,
        error: Consumer<Throwable>
    ): Disposable
}