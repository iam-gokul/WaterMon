package com.gokul.watermon.ui.autolocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gokul.watermon.R
import com.gokul.watermon.data.model.WaterModel
import com.gokul.watermon.data.network.AutoLocation.Companion.MIN_DISTANCE
import com.gokul.watermon.ui.landing.MainActivity
import java.lang.Exception
import java.util.*
import kotlinx.android.synthetic.main.activity_auto_location.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class AutoLocation : AppCompatActivity() {

    companion object {
        val REQUEST_CODE: Int = 1234
        val MIN_TIME: Long = 1
        val MIN_DISTANCE: Float = 1F
        var models: ArrayList<WaterModel>? = null


    }
    private var context: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_location)

        context = this

        var Location_provider: String = LocationManager.GPS_PROVIDER
        var currentAddress: Array<String>? = null
        val mLocationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val geocoder: Geocoder = Geocoder(this, Locale.getDefault())

        val returnedFileContent = readFileFromRawDirectory(R.raw.water_quality)
        val arr = returnedFileContent.split(",").toTypedArray()
        val columns = 0
        models = ArrayList()

        for (i in 0 until 147350 / 8) {
            val index = i * 8
            val state = arr[index]
            val dis = arr[index + 1]
            val quality = arr[index + 2]
            val river = arr[index + 3]
            val minPh = arr[index + 4]
            val maxPh = arr[index + 5]
            val minTemp = arr[index + 6]
            val maxTemp = arr[index + 7]
            val modal = WaterModel(state, dis, quality, river, minPh, maxPh, minTemp, maxTemp)
            models!!.add(modal)
        }

        Log.d("asdf", models!![0].district+" "+ models!![0].quality)




        var mLocationListener: LocationListener = object : LocationListener {
            @SuppressLint("SetTextI18n")
            override fun onLocationChanged(location: Location?) {

                var address: List<Address>? = null
                try {
                    address = geocoder.getFromLocation(location!!.latitude, location!!.longitude, 1)
                    currentAddress = address[0].getAddressLine(0).split(",").toTypedArray()
                    district_tv.text = currentAddress!!.get(4)
                    state_tv.text = currentAddress!!.get(5)
                    var district : String? = currentAddress!!.get(5).trim()
                    Log.d("asdf", district?.toUpperCase())
                    for(model in models!!){
                        if(model.district.equals(district?.toUpperCase())) {
                            qualityparameter_tv.text="Quality: "+model.quality
                            longestriver_tv.text = "Longest River : "+model.river
                            ph_tv.text = "Max Ph :" + model.maxPh+" Min Ph : "+model.minPh
                            temp_tv.text = "Max temp: "+ model.maxTemp + " Min Temp : "+model.minTemp
                        }
                    }


                } catch (e: Exception) {

                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }

        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return
        }
        mLocationManager!!.requestLocationUpdates(Location_provider, 600000, MIN_DISTANCE, mLocationListener)



    }
    private fun readFileFromRawDirectory(resourceId: Int): String {
        val iStream = context!!.resources.openRawResource(resourceId)
        var byteStream: ByteArrayOutputStream? = null
        try {
            val buffer = ByteArray(iStream.available())
            iStream.read(buffer)
            byteStream = ByteArrayOutputStream()
            byteStream.write(buffer)
            byteStream.close()
            iStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteStream.toString()
    }



}
