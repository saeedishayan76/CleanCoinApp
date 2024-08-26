package com.shayan.testing.util.repository


import com.shayan.data.model.ChartModel
import com.shayan.data.model.Coin
import com.shayan.data.repository.CoinRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeCoinRepoImpl() : CoinRepository {
    private val coinFlow = MutableSharedFlow<Result<List<Coin>>>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val chartFlow = MutableSharedFlow<Result<List<ChartModel>>>(replay = 1)
    override fun getCoins(): Flow<Result<List<Coin>>> = coinFlow

    override fun getChartData(id: String): Flow<Result<List<ChartModel>>> = chartFlow

    fun sendCoin(coinsResult: List<Coin>, isSuccess: Boolean) {
        if (isSuccess)
            coinFlow.tryEmit(Result.success(coinsResult))
        else
            coinFlow.tryEmit(Result.failure(Throwable("error")))
    }

    fun sendChart(charts: List<ChartModel>, isSuccess: Boolean) {
        if (isSuccess)
        chartFlow.tryEmit(Result.success(charts))
        else
            chartFlow.tryEmit(Result.failure(Throwable("chart error")))

    }


}