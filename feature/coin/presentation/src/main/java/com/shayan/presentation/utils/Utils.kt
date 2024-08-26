package com.shayan.presentation.utils

import java.text.DecimalFormat

object Utils {

    fun formatDecimal(number: Double): String {
        val decimalFormat = DecimalFormat("#0.00")
        return decimalFormat.format(number)
    }
}