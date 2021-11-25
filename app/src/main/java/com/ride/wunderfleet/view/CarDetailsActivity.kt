package com.ride.wunderfleet.view

import android.annotation.TargetApi
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.lifecycle.ViewModelProvider
import com.ride.wunderfleet.R
import com.ride.wunderfleet.databinding.ActivityCarDetailsBinding
import com.ride.wunderfleet.models.CarRentModel
import com.ride.wunderfleet.viewmodel.CarDetailsViewModel

class CarDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCarDetailsBinding
    lateinit var viewModel: CarDetailsViewModel
    private var carId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CarDetailsViewModel::class.java)
        binding.viewModel = viewModel
        getData()
        apiCall()
        setListners()
    }

    private fun setListners() {
        binding.btnSubmit.setOnClickListener {
            val dialog: Dialog? = getAppCompactDialog(isCancelable = false)
            dialog?.show()
            val params = CarRentModel()
            params.carId = carId
            viewModel.rentCar(
                params,
                {
                    dialog?.dismiss()
                    finish()
                },
                {
                    dialog?.dismiss()
                    finish()
                })
        }
    }

    private fun apiCall() {
        val dialog: Dialog? = getAppCompactDialog(isCancelable = false)
        dialog?.show()
        viewModel.getCarDetails(carId.toString(), {
            dialog?.dismiss()
        }, {
            dialog?.dismiss()
        })
    }

    private fun getData() {
        carId = intent.getIntExtra("carId", 0)
    }


    @TargetApi(Build.VERSION_CODES.N)
    private fun getAppCompactDialog(
        message: String? = "Please wait...",
        isCancelable: Boolean
    ): AppCompatDialog? {
        val appCompatDialog = AppCompatDialog(this)
        try {
            appCompatDialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
            appCompatDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            appCompatDialog.setContentView(R.layout.custom_progressbar)
            appCompatDialog.setCancelable(isCancelable)
            appCompatDialog.setCanceledOnTouchOutside(isCancelable)
            (appCompatDialog.findViewById<View>(R.id.progressBar) as ProgressBar?)!!.visibility =
                View.VISIBLE
            when {
                message.isNullOrEmpty() -> {
                    (appCompatDialog.findViewById<View>(R.id.txtMessage) as TextView?)!!.visibility =
                        View.GONE
                }
                else -> {
                    (appCompatDialog.findViewById<View>(R.id.txtMessage) as TextView?)!!.visibility =
                        View.VISIBLE
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return appCompatDialog
    }
}