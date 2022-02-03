package com.nft.signdoc.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.nft.signdoc.BuildConfig
import com.nft.signdoc.data.networks.interceptor.TokenInterceptor
import com.nft.signdoc.data.networks.*
import com.nft.signdoc.data.preference.SharePrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @BaseUrl
    @Singleton
    @Provides
    fun provideBaseUrl() = "https://eqxpg9y48j.execute-api.sa-east-1.amazonaws.com"

    @TransactionUrl
    @Singleton
    @Provides
    fun provideTransactionUrl() = "https://eqxpg9y48j.execute-api.sa-east-1.amazonaws.com"

    @ContactUrl
    @Singleton
    @Provides
    fun provideContactUrl() = "https://eqxpg9y48j.execute-api.sa-east-1.amazonaws.com"

    @NFTUrl
    @Singleton
    @Provides
    fun provideNFTUrl() = "https://eqxpg9y48j.execute-api.sa-east-1.amazonaws.com"

    @Singleton
    @Provides
    fun provideTokenInterceptor(sharedPreferences: SharePrefs): Interceptor {
        return TokenInterceptor(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: Interceptor): OkHttpClient {
        val builder =  OkHttpClient.Builder()
            .addNetworkInterceptor(tokenInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
        // Use log level Header or Non. Log level Body can cause out of memory issues
        // while uploading large video or image
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideBaseService(
        @BaseUrl baseUrl: String,
        httpClient: OkHttpClient
    ): Api {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionService(
        @BaseUrl transactionUrl: String,
        httpClient: OkHttpClient
    ): TransactionApi {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(transactionUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNftService(
        @NFTUrl url: String,
        httpClient: OkHttpClient
    ): NFTApi {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NFTApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContactService(
        @ContactUrl url: String,
        httpClient: OkHttpClient
    ): ContactApi {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ContactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(
        @BaseUrl url: String,
        httpClient: OkHttpClient
    ): UserApi {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginService(
        @BaseUrl url: String,
        httpClient: OkHttpClient
    ): LoginApi {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }
}