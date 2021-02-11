package `in`.crazybytes.currencyconverter.ui

import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Thursday, 11 February, 2021 at 8:16 AM
 */

@HiltViewModel
class EnterAmountViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider
) : ViewModel() {


    private val _enteredAmount = MutableLiveData<String>()
    val enteredAmount: LiveData<String> = _enteredAmount


    fun appendNumber(number: String) {
        // TODO: 11-Feb-21 Add Logic to append number
    }

    fun appendDecimal(decimal: String) {
        // TODO: 11-Feb-21 Add Logic to append decimal
    }




    fun setEnteredAmount(amountStr: String) {
        _enteredAmount.value = amountStr
    }

}