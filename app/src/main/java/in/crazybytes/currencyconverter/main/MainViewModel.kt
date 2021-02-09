package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.data.models.CurrencyRate
import `in`.crazybytes.currencyconverter.other.Helper
import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import `in`.crazybytes.currencyconverter.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
        class Success(val result: CurrencyRate) : CurrencyRateEvent()
        class Failure(val errorText: String) : CurrencyRateEvent()
        object Loading : CurrencyRateEvent()
        object Empty : CurrencyRateEvent()
    }

    private val _fromCurrency = MutableLiveData<Currency>()
    val fromCurrency: LiveData<Currency> = _fromCurrency

    private val _toCurrency = MutableLiveData<Currency>()
    val toCurrency: LiveData<Currency> = _toCurrency

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _conversion = MutableLiveData<CurrencyRateEvent>(CurrencyRateEvent.Empty)
    val conversion: LiveData<CurrencyRateEvent> = _conversion

    /**
     * converts [amountStr] from [fromCurrency] to [toCurrency] and
     * saves the result in the live data.
     */
    fun convert(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ) {
        val fromAmount = amountStr.toFloatOrNull()

        if (fromAmount == null) {
            _conversion.value = CurrencyRateEvent.Failure("Not a valid amount.")
        }

        viewModelScope.launch(dispatchers.io) {
            _conversion.postValue(CurrencyRateEvent.Loading)

            when (val ratesResponse = repository.getRates(fromCurrency)) {
                is Resource.Error -> {
                    _conversion.postValue(CurrencyRateEvent.Failure(ratesResponse.message!!))
                }
                is Resource.Success -> {
                    val rates = ratesResponse.data!!.rates
                    val rate = Helper.getRateForCurrency(toCurrency, rates)

                    if (rate == null) {
                        _conversion.postValue(CurrencyRateEvent.Failure("Unexpected Error"))
                    } else {
//                        val convertedCurrency = round(fromAmount!! * rate.rate * 100) / 100
                        _conversion.postValue(
                            CurrencyRateEvent.Success(
                                rate
                            )
                        )
                    }

                }
            }
        }
    }

    /**
     * sets [currency] as selected from currency.
     */
    fun setSelectedFromCurrency(currency: Currency) {
        _fromCurrency.postValue(currency)
    }

    /**
     * sets [currency] as selected to currency.
     */
    fun setSelectedToCurrency(currency: Currency) {
        _toCurrency.postValue(currency)
    }

    fun setAmount(amountStr: String) {
        val amount = amountStr.toFloatOrNull()
        if(amount != null) {
            _amount.postValue(String.format("%.2f", amount))
        } else {
            _amount.postValue(String.format("%.2f", 0))
        }
    }

}