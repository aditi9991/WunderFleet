package com.ride.wunderfleet.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ride.wunderfleet.R
import com.ride.wunderfleet.databinding.ActivityCarDetailsBinding
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel
import io.reactivex.functions.Consumer

class CarDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCarDetailsBinding
    lateinit var viewModel: CarDetailsViewModel
    private var carId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val params = CarDetailsViewModel.carRentModel();
                params.carId =  carId;
                viewModel.rentCar(params)            }

        })
        viewModel = ViewModelProvider(this).get(CarDetailsViewModel::class.java)
        binding.viewModel = viewModel
        getData()
        apiCall()

    }

    private fun apiCall() {
        viewModel.getCarDetails(carId.toString(), Consumer { response ->

        }, Consumer { error ->

        })
    }

    private fun getData() {
        carId = intent.getIntExtra("carId", 0)
    }

}