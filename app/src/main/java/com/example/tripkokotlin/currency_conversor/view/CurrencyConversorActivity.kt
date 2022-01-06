package com.example.tripkokotlin.currency_conversor.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tripkokotlin.R
import com.example.tripkokotlin.currency_conversor.helper.EndPoints
import com.example.tripkokotlin.currency_conversor.helper.Resource
import com.example.tripkokotlin.currency_conversor.helper.Utility
import com.example.tripkokotlin.currency_conversor.model.Rates
import com.example.tripkokotlin.currency_conversor.viewmodel.CurrencyConversorViewModel
import com.example.tripkokotlin.databinding.ActivityCurrencyConversorBinding
import com.example.tripkokotlin.main_menu.MainMenuActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CurrencyConversorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyConversorBinding

    private var selectedItem1: String? = "AFN"
    private var selectedItem2: String? = "AFN"

    //ViewModel
    private val currencyConversorViewModel: CurrencyConversorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize both Spinner
        initSpinner()

        //Click Button Home
        clickButtonHome();

        //Listen to click events
        setUpClickListener()

    }

    /**
     * This method does everything required for handling spinner (Dropdown list) - showing list of countries, handling click events on items selected.*
     */

    private fun initSpinner() {

        //get first spinner country reference in view
        val spinnerBadge = binding.fromSpinner

        //set items in the spinner i.e a list of all countries
        spinnerBadge.setItems(getAllCountries())

        //hide key board when spinner shows (For some weird reasons, this isn't so effective as I am using a custom Material Spinner)
        spinnerBadge.setOnClickListener {
            Utility.hideKeyboard(this)
        }

        //Handle selected item, by getting the item and storing the value in a  variable - selectedItem1
        spinnerBadge.setOnItemSelectedListener { view, position, id, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedItem1 = currencySymbol
            binding.txtFirstCurrencyName.setText(selectedItem1)
        }


        //get second spinner country reference in view
        val spinnerProceed = binding.toSpinner

        //hide key board when spinner shows
        spinnerProceed.setOnClickListener {
            Utility.hideKeyboard(this)
        }

        //set items on second spinner i.e - a list of all countries
        spinnerProceed.setItems(getAllCountries())


        //Handle selected item, by getting the item and storing the value in a  variable - selectedItem2,
        spinnerProceed.setOnItemSelectedListener { view, position, id, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedItem2 = currencySymbol
            binding.txtSecondCurrencyName.setText(selectedItem2)
        }

    }

    /**
     * A method for getting a country's currency symbol from the country's code
     * e.g USA - USD
     */

    private fun getSymbol(countryCode: String?): String? {
        val availableLocales = Locale.getAvailableLocales()
        for (i in availableLocales.indices) {
            if (availableLocales[i].country == countryCode
            ) return Currency.getInstance(availableLocales[i]).currencyCode
        }
        return ""
    }


    /**
     * A method for getting a country's code from the country name
     * e.g Nigeria - NG
     */

    private fun getCountryCode(countryName: String) =
        Locale.getISOCountries().find { Locale("", it).displayCountry == countryName }


    /**
     * A method for getting all countries in the world - about 256 or so
     */

    private fun
            getAllCountries(): ArrayList<String> {

        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        countries.sort()

        return countries
    }


    /**
     * A method for handling click events in the UI
     */

    private fun setUpClickListener() {

        //Convert button clicked - check for empty string and internet then do the conersion
        binding.calculateButton.setOnClickListener {

            //check if the input is empty
            val numberToConvert = binding.amountEditText.text.toString()

            if (numberToConvert.isEmpty() || numberToConvert == "0") {
                Snackbar.make(
                    binding.mainLayout,
                    "Input a value in the first text field, result will be shown in the second text field",
                    Snackbar.LENGTH_LONG
                )
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }


            //check if internet is available
            else if (!Utility.isNetworkAvailable(this)) {
                Snackbar.make(
                    binding.mainLayout,
                    "You are not connected to the internet",
                    Snackbar.LENGTH_LONG
                )
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }

            //carry on and convert the value
            else {
                doConversion()
            }
        }
    }

    /**
     * A method that does the conversion by communicating with the API - fixer.io based on the data inputed
     * Uses viewModel and flows
     */

    private fun doConversion() {

        //hide keyboard
        // Utility.hideKeyboard(this)

        //make progress bar visible
        binding.prgLoading.visibility = View.VISIBLE

        //make button invisible
        binding.calculateButton.visibility = View.GONE

        //Get the data inputed
        val apiKey = EndPoints.API_KEY
        val from = selectedItem1.toString()
        val to = selectedItem2.toString()
        val amount = binding.amountEditText.text.toString().toDouble()

        //do the conversion
        currencyConversorViewModel.getConvertedData(apiKey, from, to, amount)

        //observe for changes in UI
        observeUi()

    }

    /**
     * Using coroutines flow, changes are observed and responses gotten from the API
     *
     */

    @SuppressLint("SetTextI18n")
    private fun observeUi() {


        currencyConversorViewModel.data.observe(this, androidx.lifecycle.Observer { result ->

            when (result.status) {
                Resource.Status.SUCCESS -> {
                    if (result.data?.status == "success") {

                        val map: Map<String, Rates>

                        map = result.data.rates

                        map.keys.forEach {

                            val rateForAmount = map[it]?.rate_for_amount

                            currencyConversorViewModel.convertedRate.value = rateForAmount

                            //format the result obtained e.g 1000 = 1,000
                            val formattedString = String.format(
                                "%,.2f",
                                currencyConversorViewModel.convertedRate.value
                            )

                            //set the value in the second edit text field
                            binding.etSecondCurrency.setText(formattedString)

                        }


                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.calculateButton.visibility = View.VISIBLE
                    } else if (result.data?.status == "fail") {
                        val layout = binding.mainLayout
                        Snackbar.make(
                            layout,
                            "Ooops! something went wrong, Try again",
                            Snackbar.LENGTH_LONG
                        )
                            .withColor(ContextCompat.getColor(this, R.color.black))
                            .setTextColor(ContextCompat.getColor(this, R.color.white))
                            .show()

                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.calculateButton.visibility = View.VISIBLE
                    }
                }
                Resource.Status.ERROR -> {

                    val layout = binding.mainLayout
                    Snackbar.make(
                        layout,
                        "Oopps! Something went wrong, Try again",
                        Snackbar.LENGTH_LONG
                    )
                        .withColor(ContextCompat.getColor(this, R.color.black))
                        .setTextColor(ContextCompat.getColor(this, R.color.white))
                        .show()
                    //stop progress bar
                    binding.prgLoading.visibility = View.GONE
                    //show button
                    binding.calculateButton.visibility = View.VISIBLE
                }

                Resource.Status.LOADING -> {
                    //stop progress bar
                    binding.prgLoading.visibility = View.VISIBLE
                    //show button
                    binding.calculateButton.visibility = View.GONE
                }
            }
        })
    }

    /**
     * Method for changing the background color of snackBars
     */

    private fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
        this.view.setBackgroundColor(colorInt)
        return this
    }

    /**
     * Method for going to the MainMenu
     */

    private fun clickButtonHome() {
        binding.toolbar.homeImageButton.setOnClickListener() {
            val homeImageIntent = Intent(this, MainMenuActivity::class.java)
            startActivity(homeImageIntent)
        }
    }
}