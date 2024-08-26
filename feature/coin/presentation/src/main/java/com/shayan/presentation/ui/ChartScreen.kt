package com.shayan.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.shayan.presentation.uiModel.ChartUiModel
import com.shayan.presentation.utils.rememberCustomMarker

@Composable
fun ChartScreen(data: List<ChartUiModel>) {

    val entryData = data.map {
        FloatEntry(it.dayNumber.toFloat(), it.price.toFloat())
    }
    val entryModel = entryModelOf(entryData)


    ProvideChartStyle {
        val marker = rememberCustomMarker()
        Chart(
            chart = lineChart(
                spacing = 10.dp,
                lines = listOf(
                    LineChart.LineSpec(
                        lineColor = Color(0xff2D478E).toArgb(),
                        lineBackgroundShader = DynamicShaders.fromBrush(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xff2D478E).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                                    Color(0xff2D478E).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END),
                                )
                            )
                        ),
                        lineThicknessDp = 1f,
                        pointSizeDp = 15f,
                        point = LineComponent(
                            color = Color(0xff5493AD).toArgb(),  // Light Purple
                            shape = Shapes.pillShape,
                            strokeColor = Color.White.toArgb(),
                            strokeWidthDp = 1f
                        ),

                        ),

                    ),
            ),
            model = entryModel,
            bottomAxis = rememberBottomAxis(
                itemPlacer = AxisItemPlacer.Horizontal.default(
                    spacing = 1
                ),
                valueFormatter = AxisValueFormatter { value, _ ->
                    if (value.toInt() == 7) "Today" else
                        "Day ${value.toInt().toString()}"
                },
                guideline = null,
                axis = null,
                label = textComponent(
                    color = Color.DarkGray,  // Change this to your desired color
                    textSize = 10.sp,
                    padding = dimensionsOf(horizontal = 4.dp, vertical = 2.dp)
                )

            ),
            marker = marker,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)

        )
    }


}