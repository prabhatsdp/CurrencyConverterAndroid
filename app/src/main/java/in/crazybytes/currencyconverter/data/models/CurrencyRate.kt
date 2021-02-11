package `in`.crazybytes.currencyconverter.data.models

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 07 February, 2021 at 7:55 PM
 */

data class CurrencyRate(
    val rate: Double,
    val currency: Currency
) {
    override fun toString(): String {
        return "CurrencyRate {rate: $rate, currency: $currency}"
    }
}
