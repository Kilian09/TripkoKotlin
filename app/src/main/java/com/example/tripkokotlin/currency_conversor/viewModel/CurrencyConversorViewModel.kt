package com.example.tripkokotlin.currency_conversor.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripkokotlin.currency_conversor.model.CurrencyConversorModel

class CurrencyConversorViewModel : ViewModel() {

    val currencyConversorModel = MutableLiveData<CurrencyConversorModel>()

}