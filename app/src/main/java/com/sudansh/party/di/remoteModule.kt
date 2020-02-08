package com.sudansh.party.di

import com.sudansh.party.BuildConfig
import com.sudansh.party.data.network.CustomerApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
	single { getOkHttpClient() }
	single { getRetrofit(get()) }
	single { getCustomApi(get()) }
}

fun getOkHttpClient(): OkHttpClient {
	return OkHttpClient.Builder().build()
}

fun getRetrofit(client: OkHttpClient): Retrofit {
	return Retrofit.Builder()
		.baseUrl(BuildConfig.BASE_URL)
		.client(client)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		.build()
}

fun getCustomApi(retrofit: Retrofit): CustomerApi {
	return retrofit.create(CustomerApi::class.java)
}