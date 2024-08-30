package com.shayan.data.repository

import com.shayan.data.dto.ApiService
import com.shayan.data.dto.toCoin
import com.shayan.data.model.ChartModel
import com.shayan.data.model.Coin
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CoinRepoImpl @Inject constructor(
    private val apiService: ApiService
) : CoinRepository {
    override fun getCoins(): Flow<Result<List<Coin>>> = channelFlow {
        try {
            val response = apiService.getCoins()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    send(Result.success(body.map { it.toCoin() }))
                }
            }else {
                send(Result.failure(Throwable("Something went wrong")))
            }
        } catch (e: Exception) {
            send(Result.failure(e))
        }
    }

    override fun getChartData(id: String): Flow<Result<List<ChartModel>>> = flow {
        try {
            val response = apiService.getChart(id)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    emit(Result.success(body.prices.mapIndexed { index, doubles ->
                        ChartModel(index + 1, doubles[1])
                    }))
                }

            }else {
                emit(Result.failure(Throwable("rate limit")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}