package com.shayan.presentation.uiModel

import androidx.compose.runtime.Stable
import com.shayan.data.model.ChartModel

@Stable
data class ChartUiModel(
    val dayNumber: Int,
    val price: Double
)


fun ChartModel.toChartUiModel(): ChartUiModel {
    return ChartUiModel(
        dayNumber, price
    )
}