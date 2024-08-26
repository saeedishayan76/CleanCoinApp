package com.shayan.data

import app.cash.turbine.test
import com.shayan.data.dto.ApiService
import com.shayan.data.dto.ChartResultDto
import com.shayan.data.repository.CoinRepoImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CoinRepoImplTest {

    private lateinit var underTest: CoinRepoImpl
    private val apiService: ApiService = mockk()

    @Before
    fun setup(){
        underTest = CoinRepoImpl(apiService)
    }
    @After
    fun tearDown(){
        clearAllMocks()
    }

    @Test
    fun coinRepoImpl_getCoin_success() = runTest {
       coEvery { apiService.getCoins() } returns Response.success(emptyList())
        underTest.getCoins().test {
            assertEquals(awaitItem(), Result.success(emptyList<Any>()))
            awaitComplete()
        }
    }
    @Test
    fun coinRepoImpl_getCoin_failed() = runTest {
        coEvery { apiService.getCoins() } returns Response.error(429, mockk(relaxed = true))
        underTest.getCoins().test {
            val item = awaitItem()
            assertTrue(item.isFailure)
            awaitComplete()
        }

    }

    @Test
    fun coinRepoImpl_getChart_success() = runTest {
        coEvery { apiService.getChart(any()) } returns Response.success(ChartResultDto(emptyList()))
        underTest.getChartData("bitcoin").test {
            assertTrue(awaitItem().isSuccess)
            awaitComplete()
        }

    }
}