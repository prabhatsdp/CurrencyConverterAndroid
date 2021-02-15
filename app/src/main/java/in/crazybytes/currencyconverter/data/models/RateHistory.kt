package `in`.crazybytes.currencyconverter.data.models

import com.github.mikephil.charting.data.Entry
import java.util.*

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 14 February, 2021 at 1:29 PM
 */

data class RateHistory(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val entries: List<Entry>,
    val labels: List<String>
)
