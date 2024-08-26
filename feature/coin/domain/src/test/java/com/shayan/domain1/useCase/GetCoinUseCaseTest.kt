package com.shauan.domain.useCase

import app.cash.turbine.test
import com.shauan.domain.usecase.GetCoinUseCase
import com.shayan.testing.util.data.coins
import com.shayan.testing.util.repository.FakeCoinRepoImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCoinUseCaseTest {

    val coinRepository = FakeCoinRepoImpl()
    lateinit var underTest: GetCoinUseCase

    @Before
    fun setup(){
        underTest = GetCoinUseCase(coinRepository)

    }

    @Test
    fun useCase_invoke_success() = runTest{
        coinRepository.sendCoin(coins, true)
        underTest.invoke().test {
            assertEquals(awaitItem(), Result.success(coins))
        }
    }

    @Test
    fun useCase_invoke_failed() = runTest{
        coinRepository.sendCoin(emptyList(), false)
        underTest.invoke().test {
            assertEquals((awaitItem().exceptionOrNull() as Throwable).message,"error")
        }
    }
}