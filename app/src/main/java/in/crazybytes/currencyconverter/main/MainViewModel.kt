package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.other.Event
import `in`.crazybytes.currencyconverter.other.Helper
import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import `in`.crazybytes.currencyconverter.utils.Resource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        class Success(val result: String) : CurrencyRateEvent()
        class Failure(val errorText: String) : CurrencyRateEvent()
        object Loading : CurrencyRateEvent()
        object Empty : CurrencyRateEvent()
    }

    private val _fromCurrency = MutableLiveData(
        Event(
            Currency(
                "USD",
                "United States Dollar",
                "$"
            )
        )
    )
    val fromCurrency: LiveData<Event<Currency>> = _fromCurrency

    private val _toCurrency = MutableLiveData(
        Event(
            Currency(
                "INR",
                "Indian Rupees",
                "₹"
            )
        )
    )
    val toCurrency: LiveData<Event<Currency>> = _toCurrency

    private val _amount = MutableLiveData(Event("1"))
    val amount: LiveData<Event<String>> = _amount

    private val _conversion = MutableLiveData<CurrencyRateEvent>(CurrencyRateEvent.Empty)
    val conversion: LiveData<CurrencyRateEvent> = _conversion

    /**
     * converts [amountStr] from [fromCurrency] to [toCurrency] and
     * saves the result in the live data.
     */
    fun convert() {

        Log.d(TAG, "convert: Convert Function Fired. === === === === === ")

        try {

            val fromAmount = amount.value!!.peekContent().toFloatOrNull()
            Log.d(
                TAG,
                "convert: Converting $fromAmount ${fromCurrency.value!!.peekContent().code} to ${toCurrency.value!!.peekContent().code}"
            )

            if (fromAmount == null) {
                _conversion.value = CurrencyRateEvent.Failure("Not a valid amount.")
                Log.d(TAG, "convert: Conversion Failed. =======================")
            }

            viewModelScope.launch(dispatchers.io) {
                _conversion.postValue(CurrencyRateEvent.Loading)
                Log.d(TAG, "convert: Conversion Loading. =========================")
                when (val ratesResponse =
                    repository.getRates(fromCurrency.value!!.peekContent().code)) {
                    is Resource.Error -> {
                        _conversion.postValue(CurrencyRateEvent.Failure(ratesResponse.message!!))
                    }
                    is Resource.Success -> {
                        val rates = ratesResponse.data!!.rates
                        val rate =
                            Helper.getRateForCurrency(toCurrency.value!!.peekContent().code, rates)
                        Log.d(TAG, "convert: Conversion Success. ===================")

                        if (rate == null) {
                            _conversion.postValue(CurrencyRateEvent.Failure("Unexpected Error"))
                            Log.d(TAG, "convert: Conversion Failed 2. =============")
                        } else {
                            val convertedCurrency = String.format("%.2f", fromAmount!! * rate)
                            _conversion.postValue(CurrencyRateEvent.Success(convertedCurrency))

                            Log.d(TAG, "convert: Conversion Success and Value posted to live data.")
                        }

                    }
                }
            }

        } catch (e: Exception) {
            _conversion.value = CurrencyRateEvent.Failure("Some Error Occurred.")
        }

    }

    fun swapCurrencies() {
        val tempCurrency = fromCurrency.value!!.peekContent()
        _fromCurrency.value = Event(toCurrency.value!!.peekContent())
        _toCurrency.value = Event(tempCurrency)

        convert()
    }

    /**
     * sets [currency] as selected from currency.
     */
    fun setSelectedFromCurrency(currency: Currency) {
        Log.d(TAG, "setSelectedFromCurrency: Setting From Currency.")
        _fromCurrency.value = Event(currency)

        convert()
    }

    /**
     * sets [currency] as selected to currency.
     */
    fun setSelectedToCurrency(currency: Currency) {
        Log.d(TAG, "setSelectedToCurrency: Setting To Currency.")
        _toCurrency.value = Event(currency)

        convert()
    }

    fun setAmount(amountStr: String) {
        val tempAmount = amountStr.toFloatOrNull()
        if (tempAmount != null) {
            _amount.value = Event(String.format("%.2f", tempAmount))
        } else {
            _amount.value = Event(String.format("%.2f", 0))
        }

        Log.d(
            TAG,
            "setAmount: AmountString = $amountStr and amount = ${amount.value!!.peekContent()}"
        )

        convert()

    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}