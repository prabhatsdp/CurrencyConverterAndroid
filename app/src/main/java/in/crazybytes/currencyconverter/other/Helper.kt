package `in`.crazybytes.currencyconverter.other

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.data.models.CurrencyRate
import `in`.crazybytes.currencyconverter.data.models.Rates

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 07 February, 2021 at 7:52 PM
 */

object Helper {

    fun getRateForCurrency(currencyCode: String, rates: Rates): CurrencyRate? =
        when (currencyCode) {
            "CAD" -> {
                CurrencyRate(
                    rates.CAD,
                    Currency(
                        currencyCode,
                        "Canadian Dollar",
                        "CA$"
                    )
                )
            }
            "HKD" -> {
                CurrencyRate(
                    rates.HKD,
                    Currency(
                        currencyCode,
                        "Hong Kong Dollar",
                        "HK$"
                    )
                )
            }
            "ISK" -> {
                CurrencyRate(
                    rates.ISK,
                    Currency(
                        currencyCode,
                        "Icelandic króna",
                        "kr"
                    )
                )
            }
            "EUR" -> {
                CurrencyRate(
                    rates.EUR,
                    Currency(
                        currencyCode,
                        "Euro",
                        "€"
                    )
                )
            }
            "PHP" -> {
                CurrencyRate(
                    rates.PHP,
                    Currency(
                        currencyCode,
                        "Philippine Peso",
                        "₱"
                    )
                )
            }
            "DKK" -> {
                CurrencyRate(
                    rates.DKK,
                    Currency(
                        currencyCode,
                        "Danish Krone",
                        "kr"
                    )
                )
            }
            "HUF" -> {
                CurrencyRate(
                    rates.HUF,
                    Currency(
                        currencyCode,
                        "Hungarian Forint",
                        "Ft"
                    )
                )
            }
            "CZK" -> {
                CurrencyRate(
                    rates.CZK,
                    Currency(
                        currencyCode,
                        "Czech Koruna",
                        "Kč"
                    )
                )
            }
            "AUD" -> {
                CurrencyRate(
                    rates.AUD,
                    Currency(
                        currencyCode,
                        "Australian Dollar",
                        "A$"
                    )
                )
            }
            "RON" -> {
                CurrencyRate(
                    rates.RON,
                    Currency(
                        currencyCode,
                        "Romanian Leu",
                        "lei"
                    )
                )
            }
            "SEK" -> {
                CurrencyRate(
                    rates.SEK,
                    Currency(
                        currencyCode,
                        "Swedish Krona",
                        "kr"
                    )
                )
            }
            "IDR" -> {
                CurrencyRate(
                    rates.IDR,
                    Currency(
                        currencyCode,
                        "Indonesian Rupiah",
                        "Rp"
                    )
                )
            }
            "INR" -> {
                CurrencyRate(
                    rates.INR,
                    Currency(
                        currencyCode,
                        "Indian Rupee",
                        "₹"
                    )
                )
            }
            "BRL" -> {
                CurrencyRate(
                    rates.BRL,
                    Currency(
                        currencyCode,
                        "Brazilian Real",
                        "R$"
                    )
                )
            }
            "RUB" -> {
                CurrencyRate(
                    rates.RUB,
                    Currency(
                        currencyCode,
                        "Russian ruble",
                        "₽"
                    )
                )
            }
            "HRK" -> {
                CurrencyRate(
                    rates.HRK,
                    Currency(
                        currencyCode,
                        "Croatian Kuna",
                        "kn"
                    )
                )
            }
            "JPY" -> {
                CurrencyRate(
                    rates.JPY,
                    Currency(
                        currencyCode,
                        "Japanese Yen",
                        "¥"
                    )
                )
            }
            "THB" -> {
                CurrencyRate(
                    rates.THB,
                    Currency(
                        currencyCode,
                        "Thai Baht",
                        "฿"
                    )
                )
            }
            "CHF" -> {
                CurrencyRate(
                    rates.CHF,
                    Currency(
                        currencyCode,
                        "Swiss Franc",
                        "CHf"
                    )
                )
            }
            "SGD" -> {
                CurrencyRate(
                    rates.SGD,
                    Currency(
                        currencyCode,
                        "Singapore Dollar",
                        "S$"
                    )
                )
            }
            "PLN" -> {
                CurrencyRate(
                    rates.PLN,
                    Currency(
                        currencyCode,
                        "Polish Złoty",
                        "zł"
                    )
                )
            }
            "BGN" -> {
                CurrencyRate(
                    rates.BGN,
                    Currency(
                        currencyCode,
                        "Bulgarian Lev",
                        "Лв."
                    )
                )
            }
            "CNY" -> {
                CurrencyRate(
                    rates.CNY,
                    Currency(
                        currencyCode,
                        "Chinese Yuan",
                        "¥"
                    )
                )
            }
            "NOK" -> {
                CurrencyRate(
                    rates.NOK,
                    Currency(
                        currencyCode,
                        "Norwegian Krone",
                        "kr"
                    )
                )
            }
            "NZD" -> {
                CurrencyRate(
                    rates.NZD,
                    Currency(
                        currencyCode,
                        "New Zealand Dollar",
                        "$"
                    )
                )
            }
            "ZAR" -> {
                CurrencyRate(
                    rates.ZAR,
                    Currency(
                        currencyCode,
                        "South African Rand",
                        "R"
                    )
                )
            }
            "USD" -> {
                CurrencyRate(
                    rates.USD,
                    Currency(
                        currencyCode,
                        "United States Dollar",
                        "$"
                    )
                )
            }
            "MXN" -> {
                CurrencyRate(
                    rates.MXN,
                    Currency(
                        currencyCode,
                        "Mexican Peso",
                        "Mex$"
                    )
                )
            }
            "ILS" -> {
                CurrencyRate(
                    rates.ILS,
                    Currency(
                        currencyCode,
                        "Israeli Shekel",
                        "₪"
                    )
                )
            }
            "GBP" -> {
                CurrencyRate(
                    rates.GBP,
                    Currency(
                        currencyCode,
                        "Pound",
                        "£"
                    )
                )
            }
            "KRW" -> {
                CurrencyRate(
                    rates.KRW,
                    Currency(
                        currencyCode,
                        "South Korean Won",
                        "₩"
                    )
                )
            }
            "MYR" -> {
                CurrencyRate(
                    rates.MYR,
                    Currency(
                        currencyCode,
                        "Malaysian Ringgit",
                        "RM"
                    )
                )
            }
            else -> null
        }

    val currencyList: List<Currency> = listOf(
        Currency("CAD", "Canadian Dollar", "CA$"),
        Currency("HKD", "Hong Kong Dollar", "HK$"),
        Currency("ISK", "Icelandic króna", "kr"),
        Currency("EUR", "Euro", "€"),
        Currency(
            "PHP",
            "Philippine Peso",
            "₱"
        ),

        Currency(
            "DKK",
            "Danish Krone",
            "kr"
        ),

        Currency(
            "HUF",
            "Hungarian Forint",
            "Ft"
        ),

        Currency(
            "CZK",
            "Czech Koruna",
            "Kč"
        ),
        Currency(
            "AUD",
            "Australian Dollar",
            "A$"
        ),
        Currency(
            "RON",
            "Romanian Leu",
            "lei"
        ),
        Currency(
            "SEK",
            "Swedish Krona",
            "kr"
        ),
        Currency(
            "IDR",
            "Indonesian Rupiah",
            "Rp"
        ),
        Currency(
            "INR",
            "Indian Rupee",
            "₹"
        ),
        Currency(
            "BRL",
            "Brazilian Real",
            "R$"
        ),
        Currency(
            "RUB",
            "Russian ruble",
            "₽"
        ),
        Currency(
            "HRK",
            "Croatian Kuna",
            "kn"
        ),
        Currency(
            "JPY",
            "Japanese Yen",
            "¥"
        ),
        Currency(
            "THB",
            "Thai Baht",
            "฿"
        ),
        Currency(
            "CHF",
            "Swiss Franc",
            "CHf"
        ),
        Currency(
            "SGD",
            "Singapore Dollar",
            "S$"
        ),
        Currency(
            "PLN",
            "Polish Złoty",
            "zł"
        ),
        Currency(
            "BGN",
            "Bulgarian Lev",
            "Лв."
        ),
        Currency(
            "CNY",
            "Chinese Yuan",
            "¥"
        ),
        Currency(
            "NOK",
            "Norwegian Krone",
            "kr"
        ),
        Currency(
            "NZD",
            "New Zealand Dollar",
            "$"
        ),
        Currency(
            "ZAR",
            "South African Rand",
            "R"
        ),
        Currency(
            "USD",
            "United States Dollar",
            "$"
        ),
        Currency(
            "MXN",
            "Mexican Peso",
            "Mex$"
        ),
        Currency(
            "ILS",
            "Israeli Shekel",
            "₪"
        ),
        Currency(
            "GBP",
            "Pound",
            "£"
        ),
        Currency(
            "KRW",
            "South Korean Won",
            "₩"
        ),
        Currency(
            "MYR",
            "Malaysian Ringgit",
            "RM"
        )
    )

}