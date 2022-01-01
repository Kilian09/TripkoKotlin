package com.example.tripkokotlin.currency_conversor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityMainMenuBinding

class CurrencyConversorActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}