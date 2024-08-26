package com.shayan.presentation.ui.viewModel

import app.cash.turbine.test
import com.shauan.domain.usecase.GetChartUseCase
import com.shauan.domain.usecase.GetCoinUseCase
import com.shayan.presentation.ui.CoinViewModel
import com.shayan.presentation.ui.contract.CoinContract
import com.shayan.presentation.uiModel.toChartUiModel
import com.shayan.testing.util.MainDispatcherRule
import com.shayan.testing.util.data.charts
import com.shayan.testing.util.data.coins
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinViewModelTest {
    private val getCoinUseCase: GetCoinUseCase = mockk()
    private val getChartUseCase: GetChartUseCase = mockk()
    private lateinit var underTest: CoinViewModel

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        // use UnconfinedTestDispatchers for eagerly run test
        underTest = CoinViewModel(getCoinUseCase, getChartUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun coinsViewModel_initialize_successState() = runTest {
        every { getCoinUseCase.invoke() } returns flowOf(Result.success(coins))
        underTest.initialize()

        assertTrue(underTest.viewState.value.coins.isNotEmpty())
        assertEquals(underTest.viewState.value.coins[0].id, "bitcoin")
    }

    @Test
    fun coinsViewModel_initialize_failedState() = runTest {
        every { getCoinUseCase.invoke() } returns flowOf(Result.failure(Throwable("error")))
        underTest.initialize()
        assertEquals(underTest.viewState.value.errorMsg, "error")
    }

    @Test
    fun coinsViewModel_fetchChart_successState() = runTest {
        every { getChartUseCase.invoke(any()) } returns flowOf(Result.success(charts))
        underTest.fetchChart("bitcoin", "BTC")

        assertEquals(underTest.viewState.value.chartSymbol, "BTC/USD")
        assertEquals(underTest.viewState.value.chartData, charts.map { it.toChartUiModel() })
    }

    @Test
    fun coinsViewModel_newIntent_IntentUpdate() = runTest {
        every { getChartUseCase.invoke(any()) } returns flowOf(Result.success(emptyList()))
        underTest.event.test {
            // because SharedFlow is Hot
            underTest.setEvent(CoinContract.CoinIntent.CoinClicked("bitcoin", "BTC"))
            assertEquals(awaitItem(), CoinContract.CoinIntent.CoinClicked("bitcoin", "BTC"))
        }

    }

    @Test
    fun coinsViewModel_fetchChart_failedState() = runTest {
        every { getChartUseCase.invoke(any()) } returns flowOf(Result.failure(Throwable("error")))

        underTest.effect.test {
            underTest.fetchChart("bitcoin", "BTC")
           assertEquals(awaitItem(), CoinContract.CoinEffect.ShowError("error"))
        }

        assertEquals(underTest.viewState.value.chartSymbol, "")


    }
}