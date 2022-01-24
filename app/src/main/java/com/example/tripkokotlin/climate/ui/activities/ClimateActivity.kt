package com.example.tripkokotlin.climate.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityClimateBinding


class ClimateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityClimateBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityClimateBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}