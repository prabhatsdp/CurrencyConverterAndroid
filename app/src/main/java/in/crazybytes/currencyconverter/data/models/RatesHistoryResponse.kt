package `in`.crazybytes.currencyconverter.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 14 February, 2021 at 12:57 PM
 */

data class RatesHistoryResponse(
    @SerializedName("rates")
    val ratesHistory: Map<Date, JsonObject>
)
