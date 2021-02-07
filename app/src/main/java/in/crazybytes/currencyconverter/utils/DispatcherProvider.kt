package `in`.crazybytes.currencyconverter.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 07 February, 2021 at 7:45 AM
 */

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}