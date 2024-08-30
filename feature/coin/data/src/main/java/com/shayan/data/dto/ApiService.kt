package com.shayan.data.dto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    suspend fun getCoins(): Response<List<CoinDto>>

    @GET("coins/{id}/market_chart?vs_currency=usd&days=6&interval=daily&precision=2")
    suspend fun getChart(@Path("id") coinId: String): Response<ChartResultDto>
}