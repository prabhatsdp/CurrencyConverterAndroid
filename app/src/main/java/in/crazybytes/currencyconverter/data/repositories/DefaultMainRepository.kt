package `in`.crazybytes.currencyconverter.data.repositories

import `in`.crazybytes.currencyconverter.data.CurrencyRateApi
import `in`.crazybytes.currencyconverter.data.models.CurrencyRatesResponse
import `in`.crazybytes.currencyconverter.data.models.RatesHistoryResponse
import `in`.crazybytes.currencyconverter.utils.Resource
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 8:04 PM
 */

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyRateApi
) : MainRepository {

    override suspend fun getRates(base: String): Resource<CurrencyRatesResponse> {
        return try {

            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Error Occurred.")
        }
    }

//    override suspend fun getRatesHistory(
//        startAt: String,
//        endAt: String,
//        base: String,
//        symbols: String
//    ): Resource<RatesHistoryResponse> {
//
//        return try {
//            val response = api.getRatesHistory(startAt, endAt, base, symbols)
//            val result = response.body()
//            if (response.isSuccessful && result != null) {
//                Resource.Success(result)
//            } else {
//                Resource.Error(response.message())
//            }
//
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "An Error Occurred while fetching history.")
//        }
//
//
//    }
}