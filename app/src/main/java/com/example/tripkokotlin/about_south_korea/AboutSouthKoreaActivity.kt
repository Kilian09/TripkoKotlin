package com.example.tripkokotlin.about_south_korea

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityAboutSouthKoreaBinding
import com.example.tripkokotlin.climate.ui.activities.ClimateActivity

class AboutSouthKoreaActivity:  AppCompatActivity() {
    private lateinit var binding: ActivityAboutSouthKoreaBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityAboutSouthKoreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goClimate()
    }

    fun goClimate() {
        binding.climateBtn.setOnClickListener() {
            val climateIntent = Intent(this, ClimateActivity::class.java)
            startActivity(climateIntent)
        }
    }
}
