package com.ride.wunderfleet

import com.ride.wunderfleet.models.CarDetailsResponse
import com.ride.wunderfleet.models.CarRentResponse
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel
import io.reactivex.Observable
import retrofit2.http.*


interface RetroService {

    @GET("cars.json")
    fun geCarListFromApi(): Observable<CarsResponse>

    @GET("cars/{carId}")
    fun geCarDetails(@Path("carId") carId: String): Observable<CarDetailsResponse>

    @Headers("Content-Type: */*", "Accept: */*")
    @POST("wunderfleet-recruiting-mobile-dev-quick-rental")
    fun rentCar(@Header("Authorization")authorization: String,@Body parameters: CarDetailsViewModel.carRentModel) : Observable<CarRentResponse>

}
