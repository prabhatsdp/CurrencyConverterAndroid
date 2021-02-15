package `in`.crazybytes.currencyconverter.data.repositories

import `in`.crazybytes.currencyconverter.data.models.CurrencyRatesResponse
import `in`.crazybytes.currencyconverter.data.models.RatesHistoryResponse
import `in`.crazybytes.currencyconverter.utils.Resource
import com.google.gson.annotations.SerializedName

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 7:50 PM
 */

interface MainRepository {

    suspend fun getRates(base: String) : Resource<CurrencyRatesResponse>

    suspend fun getRatesHistory(
        startAt: String,
        endAt: String,
        base: String,
        symbols: String
    ) : Resource<RatesHistoryResponse>

}