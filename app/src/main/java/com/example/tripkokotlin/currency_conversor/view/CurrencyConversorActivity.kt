package com.example.tripkokotlin.currency_conversor.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tripkokotlin.R
import com.example.tripkokotlin.currency_conversor.viewModel.CurrencyConversorViewModel
import com.example.tripkokotlin.databinding.ActivityCurrencyConversorBinding
import com.example.tripkokotlin.main_menu.MainMenuActivity

class CurrencyConversorActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyConversorBinding

    private val currencyConversorViewModel : CurrencyConversorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currencyConversorViewModel.currencyConversorModel.observe(this, Observer {

        })

        binding.toolbar.homeImageButton.setOnClickListener(){
            val homeImageIntent = Intent(this, MainMenuActivity::class.java)
            startActivity(homeImageIntent)
        }

        binding.calculateButton.setOnClickListener(){

        }

    }
}