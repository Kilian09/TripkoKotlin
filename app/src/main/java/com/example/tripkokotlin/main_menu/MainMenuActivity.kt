package com.example.tripkokotlin.main_menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityMainMenuBinding
import com.example.tripkokotlin.gastronomy.GastronomyActivity
import com.example.tripkokotlin.about_south_korea.AboutSouthKoreaActivity


class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goGastronomy()
        goAboutSouthKorea()
    }

    fun goAboutSouthKorea() {
        binding.aboutKoreaBtn.setOnClickListener() {
            val aboutSouthKoreaIntent = Intent(this, AboutSouthKoreaActivity::class.java)
            startActivity(aboutSouthKoreaIntent)
        }
    }

    fun goGastronomy() {
        binding.gastronomyBtn.setOnClickListener() {
            val gastronomyIntent = Intent(this, GastronomyActivity::class.java)
            startActivity(gastronomyIntent)
        }
    }

}