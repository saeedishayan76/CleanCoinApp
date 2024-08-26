package com.shayan.presentation.uiModel

import androidx.compose.runtime.Stable
import com.shayan.data.model.Coin
import com.shayan.presentation.utils.Utils

@Stable
data class CoinUiModel(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val price: String,
    val priceChange: String,
    val isPriceChangeNegative: Boolean = priceChange.toDouble() < 0
)

fun Coin.toCoinUiModel(): CoinUiModel {
    return CoinUiModel(
        name = name,
        id = id,
        image = image,
        price =  Utils.formatDecimal(price.toDouble()),
        priceChange = Utils.formatDecimal(priceChange.toDouble()),
        symbol = symbol
    )
}
