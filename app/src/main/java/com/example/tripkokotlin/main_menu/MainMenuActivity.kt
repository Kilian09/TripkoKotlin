package com.example.tripkokotlin.main_menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tripkokotlin.R
import com.example.tripkokotlin.currency_conversor.view.CurrencyConversorActivity
import com.example.tripkokotlin.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.currencyConversorBtn.setOnClickListener(){
            val currencyConversorIntent = Intent(this, CurrencyConversorActivity::class.java)
            startActivity(currencyConversorIntent)
        }
    }
}