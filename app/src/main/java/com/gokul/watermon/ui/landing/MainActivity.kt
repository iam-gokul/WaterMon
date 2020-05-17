package com.gokul.watermon.ui.landing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gokul.watermon.R
import com.gokul.watermon.data.model.WaterModel
import com.gokul.watermon.databinding.ActivityMainBinding
import com.gokul.watermon.ui.autolocation.AutoLocation
import com.gokul.watermon.ui.manualocation.ManualLocation
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() , LocationListener{
    companion object {
        var models: ArrayList<WaterModel>? = null
    }
    private var context: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,0)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewmodel = ViewModelProviders.of(this).get(ViewModel :: class.java)
        binding.viewmodel = viewmodel
        viewmodel.locationListener = this


    }





    override fun onAutoClicked() {
        Toast.makeText(this,"Location Loaded",Toast.LENGTH_LONG)
        intent = Intent(this, AutoLocation::class.java)
        startActivity(intent)

    }

    override fun onCustomClicked() {
        Toast.makeText(this,"Location Loaded",Toast.LENGTH_LONG)
        intent = Intent(this, ManualLocation::class.java)
        startActivity(intent)
    }

}
