package com.gokul.watermon.ui.insights

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gokul.watermon.R
import kotlinx.android.synthetic.main.activity_insight.*


class InsightActivity : AppCompatActivity() {

    var flurideDisease1 : String = "Dental Fluorosis"
    var flurideDisease2 : String = "Skeletal Fluorosis"
    var flurideDisease3 : String = "Neurological Problem"
    var flurideCause : String = "Acute high-level exposure to fluoride is rare and usually due to accidental contamination of drinking-water or due to fires or explosions. Moderate-level chronic exposure (above 1.5 mg/litre of water - the WHO guideline value for fluoride in water) is more common"

    var salinityDisease1: String = "Death of native vegetation "
    var salinityDisease2: String = "Oesophagus Cancer "
    var salinityDisease3: String = "Stomach issues and Ulcer "
    var salinityCause: String = "Water salinity has been identified as an increasing public health concern affecting thousands of households every year. Consumption of excess saline in drinking water has been linked to a variety of health effects."

    var ironDisease1: String = "Poor skin"
    var ironDisease2: String = "Iron overload"
    var ironDisease3: String = "Kidney Issues"
    var ironCause: String = "Without the right water treatment, iron can lead to a metallic taste in food and drink. Overall, a bad taste from drinking and cooking water is never a good sign. So, while normal levels of iron in drinking water won’t have a negative impact on human health or wellbeing, excessive amounts can certainly do harm."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insight)
        val quality :String = intent.getStringExtra("quality")

        if(quality.trim().equals("Flouride")){
            disease1.text = flurideDisease1
            disease2.text = flurideDisease2
            disease2.text = flurideDisease3
            cause.text = flurideCause
        }
        if(quality.trim().equals("Salinity")){
            disease1.text = salinityDisease1
            disease2.text = salinityDisease2
            disease2.text = salinityDisease3
            cause.text = salinityCause
        }
        else {
            disease1.text = ironDisease1
            disease2.text = ironDisease2
            disease2.text = ironDisease3
            cause.text = ironCause
        }
        knowmore.setOnClickListener(){
            val url = "https://www.cdc.gov/healthywater/drinking/public/water_diseases.html"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }





    }
}
