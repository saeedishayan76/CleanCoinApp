package com.shayan.presentation.ui

import androidx.lifecycle.viewModelScope
import com.shauan.domain.usecase.GetChartUseCase
import com.shauan.domain.usecase.GetCoinUseCase
import com.shayan.presentation.BaseViewModel
import com.shayan.presentation.ui.contract.CoinContract
import com.shayan.presentation.uiModel.toChartUiModel
import com.shayan.presentation.uiModel.toCoinUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val getChartUseCase: GetChartUseCase
) : BaseViewModel<CoinContract.CoinIntent, CoinContract.CoinUiState, CoinContract.CoinEffect>() {

    fun initialize() {
        getCoinUseCase().onEach {
            it.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        errorMsg = it.message ?: "Something went wrong"
                    )
                }
            }
            it.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        coins = it.map { it.toCoinUiModel() }
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchChart(id: String, symbol: String) {

        getChartUseCase.invoke(id).onEach {
            it.onFailure {
                setState {
                    copy(
                        errorMsg = it.message ?: "Something went wrong"
                    )
                }
                setEffect {
                    CoinContract.CoinEffect.ShowError(it.message ?: "Something went wrong")
                }
            }
            it.onSuccess {
                setState {
                    copy(
                        chartSymbol = "${symbol.uppercase()}/USD"
                    )
                }
                setState {
                    copy(
                        chartData = it.map { it.toChartUiModel() }
                    )
                }

            }
        }
            .launchIn(viewModelScope)
    }

    override fun setInitialState(): CoinContract.CoinUiState = CoinContract.CoinUiState()

    override fun handleEvents(event: CoinContract.CoinIntent) {
        when (event) {
            is CoinContract.CoinIntent.CoinClicked ->
                fetchChart(event.id, event.symbol)
        }
    }


}