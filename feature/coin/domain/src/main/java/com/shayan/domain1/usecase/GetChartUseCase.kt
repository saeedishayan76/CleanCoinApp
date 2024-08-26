package com.shauan.domain.usecase

import javax.inject.Inject

class GetChartUseCase @Inject constructor(
    private val coinRepository: com.shayan.data.repository.CoinRepository
) {
    operator fun invoke(id: String)  = coinRepository.getChartData(id)
}