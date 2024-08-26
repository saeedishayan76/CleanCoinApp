package com.shayan.data.repository

import com.shayan.data.model.ChartModel
import com.shayan.data.model.Coin
import kotlinx.coroutines.flow.Flow


interface CoinRepository {
    fun getCoins(): Flow<Result<List<Coin>>>

    fun getChartData(id: String): Flow<Result<List<ChartModel>>>
}