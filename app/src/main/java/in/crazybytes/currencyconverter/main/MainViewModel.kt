package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.other.Helper
import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import `in`.crazybytes.currencyconverter.utils.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 8:08 PM
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyRateEvent {
        class Success(val resultText: String) : CurrencyRateEvent()
        class Failure(val errorText: String) : CurrencyRateEvent()
        object Loading : CurrencyRateEvent()
        object Empty : CurrencyRateEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyRateEvent>(CurrencyRateEvent.Empty)
    val conversion: StateFlow<CurrencyRateEvent> = _conversion

    fun convert(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ) {
        val fromAmount = amountStr.toFloatOrNull()

        if(fromAmount == null) {
            _conversion.value = CurrencyRateEvent.Failure("Not a valid amount.")
        }

        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyRateEvent.Loading

            when(val ratesResponse = repository.getRates(fromCurrency)) {
                is Resource.Error -> {
                    _conversion.value = CurrencyRateEvent.Failure(ratesResponse.message!!)
                }
               is Resource.Success -> {
                   val rates = ratesResponse.data!!.rates
                   val rate = Helper.getRateForCurrency(toCurrency, rates)

                   if(rate == null) {
                       _conversion.value = CurrencyRateEvent.Failure("Unexpected Error")
                   } else {
                       val convertedCurrency = round(fromAmount!! * rate.rate * 100) / 100
                       _conversion.value = CurrencyRateEvent.Success(
                           "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                       )
                   }

               }
            }
        }
    }


}