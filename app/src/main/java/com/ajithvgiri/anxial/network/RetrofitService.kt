package com.ajithvgiri.anxial.network

import android.content.Context
import com.ajithvgiri.anxial.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService(var context: Context) {

    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(context.cacheDir, cacheSize)

    private var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private var interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder().apply {
        readTimeout(60, TimeUnit.SECONDS)
        cache(myCache)
        connectTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(interceptor)
        }
        addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Client-Service", "frontend-client")
                .addHeader("Content-Type", "application/json")
                .header("Cache-Control", "public, max-age=" + 30)
                .build()

            chain.proceed(request)
        }
    }.build()

    private val retrofit = Builder()
        .baseUrl("https://evmair.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}