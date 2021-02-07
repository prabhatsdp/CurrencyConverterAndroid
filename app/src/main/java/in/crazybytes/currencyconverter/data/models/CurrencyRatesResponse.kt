package `in`.crazybytes.currencyconverter.data.models

data class CurrencyRatesResponse(
    val base: String,
    val date: String,
    val rates: Rates
)