package com.example.tripkokotlin.gastronomy

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
  suspend  fun getSouthKoreaStates(@Url url:String): Response<GastronomyResponse>
}