package com.example.tripkokotlin.currency_conversor.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripkokotlin.currency_conversor.data.model.CurrencyConversorModel

class CurrencyConversorViewModel : ViewModel() {

    val currencyConversorModel = MutableLiveData<CurrencyConversorModel>()

}