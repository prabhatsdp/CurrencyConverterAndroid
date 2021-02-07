package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.data.CurrencyRateApi
import `in`.crazybytes.currencyconverter.data.models.CurrencyRatesResponse
import `in`.crazybytes.currencyconverter.utils.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 8:04 PM
 */

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyRateApi
) : MainRepository {

    override suspend fun getRates(base: String): Resource<CurrencyRatesResponse> {
        return  try {

            val response = api.getRates(base)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Resource.Error(e.message?: "An Error Occurred.")
        }
    }

}