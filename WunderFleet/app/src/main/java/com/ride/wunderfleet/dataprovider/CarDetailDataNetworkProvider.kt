package com.ride.wunderfleet.dataprovider

import com.ride.wunderfleet.RetroService
import com.ride.wunderfleet.models.CarDetailsResponse
import com.ride.wunderfleet.models.CarRentResponse
import com.ride.wunderfleet.network.RestClient
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
//Retrofit Network calls Provider
class CarDetailDataNetworkProvider:CarDetailDataProvider {

    private val carServices = RestClient.client!!.create(RetroService::class.java)
    private val carRentServices = RestClient.clientRentCar!!.create(RetroService::class.java)

    override fun getCarDetails(
        carId:String,
        success: Consumer<CarDetailsResponse>,
        error: Consumer<Throwable>
    ): Disposable {
        return carServices.geCarDetails(carId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { response ->
                success.accept(response)
            }, error)
    }

    override fun rentCar(
        rencarRentModel: CarDetailsViewModel.carRentModel,
        success: Consumer<CarRentResponse>,
        error: Consumer<Throwable>
    ): Disposable {
        return carRentServices.rentCar("Bearer df7c313b47b7ef87c64c0f5f5cebd6086bbb0fa",rencarRentModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { response ->
                success.accept(response)
            }, error)
    }

}