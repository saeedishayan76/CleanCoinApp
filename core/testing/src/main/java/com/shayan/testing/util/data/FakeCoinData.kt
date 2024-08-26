package com.shayan.testing.util.data

import com.shayan.data.dto.CoinDto
import com.shayan.data.dto.toCoin


val coins = listOf(
    CoinDto(
        id = "bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
        price = "64234.000023",
        priceChangeInDay = "-0.134434"
    ).toCoin()
    ,
    CoinDto(
        id = "ethereum",
        name = "Ethereum",
        symbol = "eth",
        image = "https://coin-images.coingecko.com/coins/images/279/large/ethereum.png?1696501628",
        price = "2791.000023",
        priceChangeInDay = "1.234444"
    ).toCoin()
    ,
    CoinDto(
        id = "solana",
        name = "Solana",
        symbol = "sol",
        image = "https://coin-images.coingecko.com/coins/images/4128/large/solana.png?1718769756",
        price = "154.020023",
        priceChangeInDay = "-0.14"
    ).toCoin()
)