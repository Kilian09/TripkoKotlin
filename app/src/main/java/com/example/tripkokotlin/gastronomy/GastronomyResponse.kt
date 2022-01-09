package com.example.tripkokotlin.gastronomy

import com.google.gson.annotations.SerializedName

data class GastronomyResponse(
    @SerializedName("states") var states: List<String>
)