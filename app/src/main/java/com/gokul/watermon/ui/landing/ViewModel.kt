package com.gokul.watermon.ui.landing

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.gokul.watermon.ui.autolocation.AutoLocation

class ViewModel : ViewModel() {

    var locationListener : LocationListener ? = null


    fun onAutoLocationClick( view : View){
        locationListener?.onAutoClicked()
        Log.d("asdf","auto location button clickerd!")
    }

    fun onCustomLocationClick( view: View){
        locationListener?.onCustomClicked()
    }

}