package com.example.testwn.di

import com.example.testwn.api.CoinsService
import com.example.testwn.data.CoinsRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.coinranking.com/"

val networkModule = module {
    single { provideOKHttpClient() }
    single { provideRetrofit(get()) }
    single { provideCoinApi(get()) }
    single { provideRepository(get()) }
}

fun provideRepository(coinsService: CoinsService): CoinsRepository {
    return CoinsRepository(coinsService)
}

fun provideOKHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideCoinApi(retrofit: Retrofit): CoinsService {
    return retrofit.create(CoinsService::class.java)
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
