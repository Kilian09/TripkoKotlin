package com.example.tripkokotlin.currency_conversor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.databinding.ActivityCurrencyConversorBinding
import com.example.tripkokotlin.main_menu.MainMenuActivity

class CurrencyConversorActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyConversorBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.homeImageButton.setOnClickListener(){
            val homeImageIntent = Intent(this, MainMenuActivity::class.java)
            startActivity(homeImageIntent)
        }

    }
}