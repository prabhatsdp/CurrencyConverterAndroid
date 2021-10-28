package `in`.crazybytes.currencyconverter.di

import `in`.crazybytes.currencyconverter.data.CurrencyRateApi
import `in`.crazybytes.currencyconverter.data.repositories.DefaultMainRepository
import `in`.crazybytes.currencyconverter.data.repositories.MainRepository
import `in`.crazybytes.currencyconverter.utils.Constants.BASE_URL
import `in`.crazybytes.currencyconverter.utils.DispatcherProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Saturday, 06 February, 2021 at 7:43 PM
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyRateApi(): CurrencyRateApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val gson = GsonBuilder().setDateFormat("YYYY-MM-DD").create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CurrencyRateApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyRateApi): MainRepository = DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}