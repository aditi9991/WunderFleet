package com.ride.wunderfleet.network

import com.ride.wunderfleet.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {
    private var retrofit: Retrofit? = null
    private var carRetrofit: Retrofit? = null

    @get:Synchronized
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                val okHttpClient = okHttpClient
                retrofit = Retrofit.Builder()
                    .baseUrl("https://s3.eu-central-1.amazonaws.com/wunderfleet-recruiting-dev/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }

    @get:Synchronized
    val clientRentCar: Retrofit?
        get() {
            if (carRetrofit == null) {
                carRetrofit = Retrofit.Builder()
                    .baseUrl("https://4i96gtjfia.execute-api.eu-central-1.amazonaws.com/default/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return carRetrofit
        }


    private val okHttpClient: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(
                    if (BuildConfig.DEBUG) httpLoggingInterceptor.setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    ) else httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
                )
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
        }
}