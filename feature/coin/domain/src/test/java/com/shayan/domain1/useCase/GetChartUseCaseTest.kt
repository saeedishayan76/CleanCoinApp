package com.shayan.domain1.useCase

import app.cash.turbine.test
import com.shauan.domain.usecase.GetChartUseCase
import com.shayan.testing.util.data.charts
import com.shayan.testing.util.repository.FakeCoinRepoImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetChartUseCaseTest {

    private val coinRepository = FakeCoinRepoImpl()
    lateinit var underTest: GetChartUseCase

    @Before
    fun setup(){
        underTest = GetChartUseCase(coinRepository)
    }


    @Test
    fun chartUseCase_invoke_success() = runTest {
        coinRepository.sendChart(charts, true)
        underTest.invoke("bitcoin").test {
            assertEquals(awaitItem(), Result.success(charts))
        }
    }

    @Test
    fun chartUseCase_invoke_failed() = runTest{
        coinRepository.sendChart(charts, false)
        underTest.invoke("bitcoin").test {
            assertTrue(awaitItem().isFailure)
        }
    }

}