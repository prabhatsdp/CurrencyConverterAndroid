package `in`.crazybytes.currencyconverter.features.amount

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


    private val _enteredAmount = MutableLiveData<String>("")
    val enteredAmount: LiveData<String> = _enteredAmount


    fun appendNumber(number: String) {

        val prevAmount = _enteredAmount.value
        prevAmount?.let {
            if (it == "0") {
                if(number != "0") _enteredAmount.postValue(number)
            } else {
                _enteredAmount.postValue(prevAmount + number)
            }
        }

    }

    fun appendDecimal() {

        val prevAmount = enteredAmount.value
        prevAmount?.let {
            if(!it.contains(".")) _enteredAmount.postValue("$it.")
        }
    }


    fun deleteLastCharacter() {

        val prevAmount = _enteredAmount.value

        prevAmount?.let {

            if(it == "0") {
                return@let
            }

            if(it.length == 1) {
                _enteredAmount.postValue(
                    "0"
                )
            } else {
                _enteredAmount.postValue(
                    prevAmount.substring(
                        0,
                        prevAmount.length - 1
                    )
                )
            }

        }
    }



    fun setEnteredAmount(amountStr: String) {

        if(amountStr[amountStr.length - 1] == '.') {
            _enteredAmount.value = amountStr.substring(0, amountStr.length - 1)
        } else {
            _enteredAmount.value = amountStr
        }

    }

}