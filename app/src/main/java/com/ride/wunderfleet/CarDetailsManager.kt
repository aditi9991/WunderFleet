package com.ride.wunderfleet

import com.ride.wunderfleet.dataprovider.CarDetailDataNetworkProvider
import com.ride.wunderfleet.models.CarDetailsResponse
import com.ride.wunderfleet.models.CarRentModel
import com.ride.wunderfleet.models.CarRentResponse
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class CarDetailsManager(private val compositeDisposable: CompositeDisposable?) {

    private val carDetailDataNetworkProvider: CarDetailDataNetworkProvider =
        CarDetailDataNetworkProvider()

    fun getCarDetails(
        carId: String,
        response: Consumer<CarDetailsResponse>,
        error: Consumer<Throwable>
    ) {
        compositeDisposable!!.add(
            carDetailDataNetworkProvider.getCarDetails(
                carId,
                Consumer { response.accept(it) },
                error
            )
        )
    }

    fun rentCar(
        carrentModel: CarRentModel,
        response: Consumer<CarRentResponse>,
        error: Consumer<Throwable>
    ) {
        compositeDisposable!!.add(
            carDetailDataNetworkProvider.rentCar(
                carrentModel,
                Consumer { response.accept(it) },
                error
            )
        )
    }
}