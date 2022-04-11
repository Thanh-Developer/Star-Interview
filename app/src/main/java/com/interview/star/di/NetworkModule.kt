package com.interview.star.di

import com.google.gson.GsonBuilder
import com.interview.star.data.remote.api.APIService
import com.interview.star.di.Properties.CONNECTION_TIMEOUT
import com.interview.star.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule: Module = module {
    single { createRetrofit() }
    single { createApi() }
}

object Properties {
    const val CONNECTION_TIMEOUT: Long = 10
}

fun createRetrofit(): Retrofit {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        var request = chain.request()
        val url = request
            .url()
            .newBuilder()
            .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }
    httpClient.addInterceptor(createLoggingInterceptor())
    httpClient.apply {
        readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    }

    val gson = GsonBuilder().create()

    return Retrofit.Builder().baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun createApi(): APIService = createRetrofit().create(APIService::class.java)

fun createLoggingInterceptor(): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

