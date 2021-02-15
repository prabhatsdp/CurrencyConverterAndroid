package `in`.crazybytes.currencyconverter.main

import `in`.crazybytes.currencyconverter.data.models.Currency
import `in`.crazybytes.currencyconverter.data.models.RateHistory
import `in`.crazybytes.currencyconverter.other.Event
import `in`.crazybytes.currencyconverter.other.Helper
import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import `in`.crazybytes.currencyconverter.utils.Resource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
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

    private val _rateHistoryLiveData = MutableLiveData<RateHistoryEvent>(RateHistoryEvent.Empty)
    val rateHistoryLiveData: LiveData<RateHistoryEvent> = _rateHistoryLiveData

    /**
     * converts [amountStr] from [fromCurrency] to [toCurrency] and
     * saves the result in the live data.
     */
    fun convert() {

        try {

            val fromAmount = amount.value!!.peekContent().toFloatOrNull()

            if (fromAmount == null) {
                _conversion.value = CurrencyRateEvent.Failure("Not a valid amount.")

            }

            viewModelScope.launch(dispatchers.io) {
                _conversion.postValue(CurrencyRateEvent.Loading)

                when (val ratesResponse =
                    repository.getRates(fromCurrency.value!!.peekContent().code)) {

                    is Resource.Error -> {
                        _conversion.postValue(CurrencyRateEvent.Failure(ratesResponse.message!!))
                    }

                    is Resource.Success -> {
                        val rates = ratesResponse.data!!.rates
                        val rate =
                            Helper.getRateForCurrency(toCurrency.value!!.peekContent().code, rates)

                        if (rate == null) {
                            _conversion.postValue(CurrencyRateEvent.Failure("Unexpected Error"))
                        } else {

                            val rateString =
                                if (rate < 0.01) Helper.roundToFourDecimalPlacesString(rate) else Helper.roundToTwoDecimalPlacesString(
                                    rate
                                )
                            _conversion.postValue(CurrencyRateEvent.Success(rateString))

                        }
                    }

                }
            }

        } catch (e: Exception) {
            _conversion.value = CurrencyRateEvent.Failure("Some Error Occurred.")
        }

    }

    /**
     * This function fetches the exchange rate history of
     * the currencies already stored in [fromCurrency] & [toCurrency]
     * LiveData variables.
     */
    fun fetchRatesHistory() {

        try {

            val startAt = "2021-02-08"
            val endAt = "2021-02-14"
            val base = fromCurrency.value!!.peekContent().code
            val symbols = toCurrency.value!!.peekContent().code

            viewModelScope.launch(dispatchers.io) {

                _rateHistoryLiveData.postValue(RateHistoryEvent.Loading)

                val response = repository.getRatesHistory(
                    startAt, endAt, base, symbols
                )

                when (response) {

                    is Resource.Error -> {
                        _rateHistoryLiveData.postValue(RateHistoryEvent.Failure(response.message!!))
                    }

                    is Resource.Success -> {

                        val entryList = arrayListOf<Entry>()
                        val labelList = arrayListOf<String>()
                        val historyJson = response.data!!.ratesHistory
                        val sortedHistoryMap = TreeMap<Date, JsonObject>(historyJson)


                        var entryCounter = 0f;
                        for ((key, value) in sortedHistoryMap) {

                            val entry = Entry(entryCounter, value[symbols].toString().toFloat())
                            val label = Helper.formatDateToDDMMM(key)

                            entryList.add(entry)
                            labelList.add(label)
                            entryCounter++
                        }

                        val rateHistory = RateHistory(
                            fromCurrency.value!!.peekContent(),
                            toCurrency.value!!.peekContent(),
                            entryList,
                            labelList
                        )

                        _rateHistoryLiveData.postValue(RateHistoryEvent.Success(rateHistory))

                    }
                }
            }

        } catch (e: Exception) {

            _rateHistoryLiveData.postValue(RateHistoryEvent.Failure(e.message!!))
        }
    }

    fun swapCurrencies() {
        val tempCurrency = fromCurrency.value!!.peekContent()
        _fromCurrency.value = Event(toCurrency.value!!.peekContent())
        _toCurrency.value = Event(tempCurrency)

        convert()
        fetchRatesHistory()
    }

    /**
     * sets [currency] as selected from currency.
     */
    fun setSelectedFromCurrency(currency: Currency) {
        Log.d(TAG, "setSelectedFromCurrency: Setting From Currency.")
        _fromCurrency.value = Event(currency)

        convert()
        fetchRatesHistory()
    }

    /**
     * sets [currency] as selected to currency.
     */
    fun setSelectedToCurrency(currency: Currency) {
        Log.d(TAG, "setSelectedToCurrency: Setting To Currency.")
        _toCurrency.value = Event(currency)

        convert()
        fetchRatesHistory()
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


    sealed class CurrencyRateEvent {
        class Success(val result: String) : CurrencyRateEvent()
        class Failure(val errorText: String) : CurrencyRateEvent()
        object Loading : CurrencyRateEvent()
        object Empty : CurrencyRateEvent()
    }

    sealed class RateHistoryEvent {
        class Success(val result: RateHistory) : RateHistoryEvent()
        class Failure(val errorText: String) : RateHistoryEvent()
        object Loading : RateHistoryEvent()
        object Empty : RateHistoryEvent()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}