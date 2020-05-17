package com.gokul.watermon.data.model

data class WaterModel (

    var state : String ? = null,
    var district : String ? = null,
    var quality : String ? = null,
    var river : String ? = null,
    val minPh : String? = null,
    val maxPh : String? = null,
    val minTemp : String? = null,
    val maxTemp : String? = null

    )


