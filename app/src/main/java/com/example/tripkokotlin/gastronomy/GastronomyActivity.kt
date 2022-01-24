package com.example.tripkokotlin.gastronomy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripkokotlin.BuildConfig
import com.example.tripkokotlin.R
import com.example.tripkokotlin.currency_conversor.helper.EndPoints
import com.example.tripkokotlin.databinding.ActivityGastronomyListBinding
import com.example.tripkokotlin.databinding.GastronomyListBinding
import com.google.gson.Gson
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@AndroidEntryPoint
class GastronomyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGastronomyListBinding
    private lateinit var adapter: GastronomyAdapter

    private val koreaStates = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TripkoKotlin)

        super.onCreate(savedInstanceState)
        binding = ActivityGastronomyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = GastronomyAdapter(koreaStates)
        binding.gastronomiaList.layoutManager = LinearLayoutManager(this)
        binding.gastronomiaList.adapter = adapter
        getSouthKoreaStates()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://countryrestapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getSouthKoreaStates() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSouthKoreaStates("/kr")
            val states = call.body()
            runOnUiThread {
                if (call.isSuccessful) {

                    val kStates = states?.states ?: emptyList()
                    koreaStates.clear()
                    koreaStates.addAll(kStates)
                    adapter.notifyDataSetChanged()

                } else {

                }
            }

        }
    }

}