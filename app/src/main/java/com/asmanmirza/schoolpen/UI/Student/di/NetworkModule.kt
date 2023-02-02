package com.asmanmirza.schoolpen.UI.Student.di

import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.data.api.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return Retrofit.Builder().baseUrl(NetworkConstants.Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesMyApi(retrofit: Retrofit): MyApi {
        return retrofit.create(MyApi::class.java)
    }
}