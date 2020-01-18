package com.example.testwn.api

import com.example.testwn.model.Coins
import retrofit2.http.GET
import retrofit2.http.Query


private val TAG = "CoinsService"

interface CoinsService {

    @GET("v1/public/coins")
    suspend fun searchCoinsByPrefix(
        @Query("timePeriod") timePeriod: String,
        @Query("prefix") prefix: String?
    ): Coins

    @GET("v1/public/coins")
    suspend fun searchCoinsBySymbols(
        @Query("timePeriod") timePeriod: String,
        @Query("symbols") symbols: String?
    ): Coins

    @GET("v1/public/coins")
    suspend fun searchCoinsBySlugs(
        @Query("timePeriod") timePeriod: String,
        @Query("slugs") slugs: String?
    ): Coins

    @GET("v1/public/coins")
    suspend fun searchCoinsByIds(
        @Query("timePeriod") timePeriod: String,
        @Query("ids")
        IDs: String?
    ): Coins

    @GET("v1/public/coins")
    suspend fun fetchCoins(
        @Query("timePeriod") timePeriod: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Long
    ): Coins


}

