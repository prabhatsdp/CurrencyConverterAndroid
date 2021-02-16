package `in`.crazybytes.currencyconverter.utils

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Thursday, 11 February, 2021 at 8:40 PM
 */

/**
 * This class is a wrapper class for the the object [T]
 * It provides the function of checking if the result
 * has been handled already or not.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}