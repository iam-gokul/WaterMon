package com.gokul.watermon.data.network

import android.content.Context
import android.location.*
import android.os.Bundle
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.checkSelfPermission
import java.lang.Exception
import java.util.*
import java.util.jar.Manifest

class AutoLocation {

    companion object {
        val REQUEST_CODE: Int = 1234
        val MIN_TIME: Long = 1
        val MIN_DISTANCE: Float = 1F
    }

    var Location_provider: String = LocationManager.GPS_PROVIDER






}


