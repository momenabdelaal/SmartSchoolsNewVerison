package com.smartschools.android.di

import android.app.Application
import android.content.Context
import com.exas.qpmoemp.data.network.NetworkServices
import com.exas.qpmoemp.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.domain.network.ErrorTypeHandler
import com.smartschools.android.domain.network.ErrorTypeHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    private const val baseUrl = "https://exastfs.alqemam.com:8443/api/"
//    private const val baseUrl = "https://iis01.exas2030.com/qPMO_V8.2/api/"
//    private const val baseUrl = "https://iis01.exas2030.com/qPMO_EmpV8.2/api/"
//    private const val baseUrl = "https://iis01.exas2030.com/qPMO_EmpV8.2.1/api/"
//    private const val baseUrl = "https://iis01.exas2030.com/qPMO_EmpV8.7/api/"
    private const val baseUrl = "https://iis01.exas2030.com/qPMO_EmpV1.1.05/api/"
    private const val serviceUrl = "https://exastfs.alqemam.com:8443/api/"

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideApiService(sharedPreferences: SharedPreferencesImpl): NetworkServices {


        val retrofit = Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    if (sharedPreferences.getApiKeyToken().isNotEmpty())
                        it.addHeader(
                            "Authorization", "Bearer " + sharedPreferences.getApiKeyToken()
                        )
                }.build())
            }.also { client ->

                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                client.connectTimeout(60, TimeUnit.SECONDS)
                client.readTimeout(60, TimeUnit.SECONDS)
                client.writeTimeout(60, TimeUnit.SECONDS)
            }.build()).build()

        return retrofit.create(NetworkServices::class.java)
    }

    @Provides
    fun provideErrorTypeHandler(errorTypeHandlerImpl: ErrorTypeHandlerImpl): ErrorTypeHandler {
        return errorTypeHandlerImpl
    }


}