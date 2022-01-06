package com.example.tripkokotlin.currency_conversor.viewmodel

import com.example.tripkokotlin.currency_conversor.model.ApiResponse
import com.example.tripkokotlin.currency_conversor.helper.Resource
import com.example.tripkokotlin.currency_conversor.network.ApiDataSource
import com.example.tripkokotlin.currency_conversor.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiDataSource: ApiDataSource): BaseDataSource() {

    //Using coroutines flow to get the response from
    suspend fun getConvertedData(access_key: String, from: String, to: String, amount: Double): Flow<Resource<ApiResponse>> {
        return flow {
            emit(safeApiCall { apiDataSource.getConvertedRate(access_key, from, to, amount) })
        }.flowOn(Dispatchers.IO)
    }

}