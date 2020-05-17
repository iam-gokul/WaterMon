package com.gokul.watermon.ui.manualocation

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.gokul.watermon.R
import com.gokul.watermon.data.model.WaterModel
import com.gokul.watermon.ui.autolocation.AutoLocation
import kotlinx.android.synthetic.main.activity_manual_location.*
import kotlinx.android.synthetic.main.activity_manual_location.longestriver_tv
import kotlinx.android.synthetic.main.activity_manual_location.ph_tv
import kotlinx.android.synthetic.main.activity_manual_location.qualityparameter_tv
import kotlinx.android.synthetic.main.activity_manual_location.temp_tv
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.ArrayList


class ManualLocation : AppCompatActivity() {

    private var context: Context? = null
    companion object{
        var models: ArrayList<WaterModel>? = null
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_location)
        context = this
        val returnedFileContent = readFileFromRawDirectory(R.raw.water_quality)
        val arr = returnedFileContent.split(",").toTypedArray()
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


        search_button.setOnClickListener(){
            if(district_ev.text!=null){
                var district: String? = district_ev.text.toString()
                for(model in models!!){
                    if(model.district.equals(district?.toUpperCase())) {
                        qualityparameter_tv.text="Quality: "+model.quality
                        longestriver_tv.text = "Longest River : "+model.river
                        ph_tv.text = "Max Ph :" + model.maxPh+" Min Ph : "+model.minPh
                        temp_tv.text = "Max temp: "+ model.maxTemp + " Min Temp : "+model.minTemp
                    }
                }
            }
        }


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
