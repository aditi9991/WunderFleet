package com.ride.wunderfleet.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ride.wunderfleet.CarsResponse
import com.ride.wunderfleet.ExtensionFuns.readAssetsFile
import com.ride.wunderfleet.R
import com.ride.wunderfleet.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var listCars: ArrayList<CarsResponse>
    private var prevMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        listCars = Gson().fromJson(
            this.assets.readAssetsFile("cars.json"),
            object : TypeToken<ArrayList<CarsResponse?>?>() {}.type
        ) as ArrayList<CarsResponse>


        listCars.forEachIndexed { pos, it ->
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.lat, it.lon))
                    .title(it.title)
            )!!.tag = pos
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(listCars[1].lat, listCars[1].lon),16.5f))

        mMap.setOnMarkerClickListener(this)
    }

    private var clickCount = 0
    override fun onMarkerClick(marker: Marker): Boolean {
        val position = marker.tag as Int
        if (prevMarker != null) {
            //Set prevMarker back to default color
            prevMarker!!.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        }

        //leave Marker default color if re-click current Marker
        if (marker != prevMarker) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            prevMarker = marker
            clickCount = 0
        } else clickCount++

        prevMarker = marker
        if (clickCount > 0) {
            val intent = Intent(this, CarDetailsActivity::class.java)
            intent.putExtra("carId", listCars[position].carId)
            startActivity(intent)
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 16.5f))
        return false
    }

}