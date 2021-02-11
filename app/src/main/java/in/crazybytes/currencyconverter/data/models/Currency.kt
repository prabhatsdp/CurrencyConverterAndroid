package `in`.crazybytes.currencyconverter.data.models

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 07 February, 2021 at 7:54 PM
 */

data class Currency(
    val code: String,
    val title: String,
    val symbol: String
) {
    override fun toString(): String {
        return "Currency { code: $code, title: $title, symbol: $symbol}"
    }
}
