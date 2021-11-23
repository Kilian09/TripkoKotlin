package com.example.tripkokotlin.main_menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        Thread.sleep(2000)

        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}