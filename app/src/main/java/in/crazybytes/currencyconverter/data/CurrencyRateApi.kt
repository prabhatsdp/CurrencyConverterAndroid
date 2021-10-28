package `in`.crazybytes.currencyconverter.data

import `in`.crazybytes.currencyconverter.data.models.CurrencyRatesResponse
import `in`.crazybytes.currencyconverter.data.models.RatesHistoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 7:40 PM
 */

interface CurrencyRateApi {

    @GET("/latest")
    suspend fun getRates(
        @Query("base") base: String
    ) : Response<CurrencyRatesResponse>


//    @GET("/history")
//    suspend fun getRatesHistory(
//        @Query("start_at") startAt: String,
//        @Query("end_at") endAt: String,
//        @Query("base") base: String,
//        @Query("symbols") symbols: String,
//    ) : Response<RatesHistoryResponse>
}