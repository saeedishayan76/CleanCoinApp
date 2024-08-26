package com.shauan.domain.usecase


import javax.inject.Inject

class GetCoinUseCase @Inject constructor (
    private val coinRepository: com.shayan.data.repository.CoinRepository
) {
    operator fun invoke() = coinRepository.getCoins()
}