package com.shayan.data.dto

import com.google.gson.annotations.SerializedName
import com.shayan.data.model.Coin


data class CoinDto(
    @SerializedName("current_price")
    val price: String,
    val id: String,
    val image: String,
    val name: String,
    @SerializedName("price_change_percentage_24h")
    val priceChangeInDay: String,
    val symbol: String,
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        price = price,
        priceChange = priceChangeInDay,
        image = image
    )
}