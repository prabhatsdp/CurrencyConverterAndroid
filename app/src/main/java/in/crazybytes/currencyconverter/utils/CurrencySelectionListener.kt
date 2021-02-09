package `in`.crazybytes.currencyconverter.utils

import `in`.crazybytes.currencyconverter.data.models.Currency

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Monday, 08 February, 2021 at 7:54 AM
 */

interface CurrencySelectionListener {
    fun onCurrencySelected(selectedCurrency: Currency)
}