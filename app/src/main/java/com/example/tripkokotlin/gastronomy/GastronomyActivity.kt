package com.example.tripkokotlin.gastronomy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.GastronomyListBinding

class GastronomyActivity : AppCompatActivity() {

        private lateinit var binding: GastronomyListBinding

        override fun onCreate(savedInstanceState: Bundle?) {

            setTheme(R.style.Theme_TripkoKotlin)

            super.onCreate(savedInstanceState)
            binding = GastronomyListBinding.inflate(layoutInflater)
            setContentView(binding.root)

        }
    }