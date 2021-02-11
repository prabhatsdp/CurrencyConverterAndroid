package `in`.crazybytes.currencyconverter.other

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.data.models.CurrencyRate
import `in`.crazybytes.currencyconverter.data.models.Rates

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 07 February, 2021 at 7:52 PM
 */

object Helper {

    fun getRateForCurrency(currencyCode: String, rates: Rates): Double? =
        when (currencyCode) {
            "CAD" -> rates.CAD
            "HKD" -> rates.HKD
            "ISK" -> rates.ISK
            "EUR" -> rates.EUR
            "PHP" -> rates.PHP
            "DKK" -> rates.DKK
            "HUF" -> rates.HUF
            "CZK" -> rates.CZK
            "AUD" -> rates.AUD
            "RON" -> rates.RON
            "SEK" -> rates.SEK
            "IDR" -> rates.IDR
            "INR" -> rates.INR
            "BRL" -> rates.BRL
            "RUB" -> rates.RUB
            "HRK" -> rates.HRK
            "JPY" -> rates.JPY
            "THB" -> rates.THB
            "CHF" -> rates.CHF
            "SGD" -> rates.SGD
            "PLN" -> rates.PLN
            "BGN" -> rates.BGN
            "CNY" -> rates.CNY
            "NOK" -> rates.NOK
            "NZD" -> rates.NZD
            "ZAR" -> rates.ZAR
            "USD" -> rates.USD
            "MXN" -> rates.MXN
            "ILS" -> rates.ILS
            "GBP" -> rates.GBP
            "KRW" -> rates.KRW
            "MYR" -> rates.MYR
            else ->  null
        }

    val currencyList: List<Currency> = listOf(
        Currency("CAD", "Canadian Dollar", "CA$"),
        Currency("HKD", "Hong Kong Dollar", "HK$"),
        Currency("ISK", "Icelandic króna", "kr"),
        Currency("EUR", "Euro", "€"),
        Currency("PHP", "Philippine Peso", "₱"),
        Currency("DKK", "Danish Krone", "kr"),
        Currency("HUF", "Hungarian Forint", "Ft"),
        Currency("CZK", "Czech Koruna", "Kč"),
        Currency("AUD", "Australian Dollar", "A$"),
        Currency("RON", "Romanian Leu", "lei"),
        Currency("SEK", "Swedish Krona", "kr"),
        Currency("IDR", "Indonesian Rupiah", "Rp"),
        Currency("INR", "Indian Rupee", "₹"),
        Currency("BRL", "Brazilian Real", "R$"),
        Currency("RUB", "Russian ruble", "₽"),
        Currency("HRK", "Croatian Kuna", "kn"),
        Currency("JPY", "Japanese Yen", "¥"),
        Currency("THB", "Thai Baht", "฿"),
        Currency("CHF", "Swiss Franc", "CHf"),
        Currency("SGD", "Singapore Dollar", "S$"),
        Currency("PLN", "Polish Złoty", "zł"),
        Currency("BGN", "Bulgarian Lev", "Лв."),
        Currency("CNY", "Chinese Yuan", "¥"),
        Currency("NOK", "Norwegian Krone", "kr"),
        Currency("NZD", "New Zealand Dollar", "$"),
        Currency("ZAR", "South African Rand", "R"),
        Currency("USD", "United States Dollar", "$"),
        Currency("MXN", "Mexican Peso", "Mex$"),
        Currency("ILS", "Israeli Shekel", "₪"),
        Currency("GBP", "Pound", "£"),
        Currency("KRW", "South Korean Won", "₩"),
        Currency("MYR", "Malaysian Ringgit", "RM")
    )

}