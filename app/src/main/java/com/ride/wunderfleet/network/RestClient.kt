package com.ride.wunderfleet.network

import com.ride.wunderfleet.BuildConfig
import retrofit2.Retrofit
import kotlin.jvm.Synchronized
import com.ride.wunderfleet.network.RestClient
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object RestClient {
    private var retrofit: Retrofit? = null

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
            if (retrofit == null) {
                val okHttpClient = okHttpClient
                retrofit = Retrofit.Builder()
                    .baseUrl("https://4i96gtjfia.execute-api.eu-central-1.amazonaws.com/default/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }


    private val okHttpClient: OkHttpClient
        private get() {
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