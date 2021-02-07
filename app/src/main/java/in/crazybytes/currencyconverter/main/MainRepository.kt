package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.data.models.CurrencyRatesResponse
import `in`.crazybytes.currencyconverter.utils.Resource

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 7:50 PM
 */

interface MainRepository {

    suspend fun getRates(base: String) : Resource<CurrencyRatesResponse>

}