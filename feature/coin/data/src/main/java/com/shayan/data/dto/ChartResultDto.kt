package com.shayan.data.dto

import com.shayan.data.model.ChartModel

data class ChartResultDto(
    val prices: List<List<Double>>,
)

fun ChartResultDto.toChartModel(): List<ChartModel> {
    return listOf(
        ChartModel(
            1, 2.00
        ),
        ChartModel(
            2, 321.0092
        ),
        ChartModel(
            3, 35.0
        ),
        ChartModel(
            4, 97.013
        ),
        ChartModel(
            5, 64222.234
        ),
        ChartModel(
            6, 7483.02
        ),
        ChartModel(
            7, 8.002003
        ))
}
