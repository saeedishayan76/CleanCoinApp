package com.shayan.presentation.ui.contract

import com.shayan.presentation.ViewEvent
import com.shayan.presentation.ViewSideEffect
import com.shayan.presentation.ViewState
import com.shayan.presentation.uiModel.ChartUiModel
import com.shayan.presentation.uiModel.CoinUiModel

object CoinContract {

    data class CoinUiState(
        val isLoading: Boolean = true,
        val coins: List<CoinUiModel> = emptyList(),
        val chartSymbol: String = "",
        val chartData: List<ChartUiModel> = emptyList(),
        val errorMsg: String = ""
    ): ViewState


    sealed class CoinIntent: ViewEvent{
        data class CoinClicked(val id: String,val symbol:String): CoinIntent()
    }

    sealed class CoinEffect : ViewSideEffect{
        data class ShowError(val message: String) : CoinEffect()

    }
}