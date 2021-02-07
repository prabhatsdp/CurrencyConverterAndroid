package `in`.crazybytes.currencyconverter.utils

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 7:55 PM
 */

sealed class Resource<T>( val data: T?, val message: String?) {

    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)

}
