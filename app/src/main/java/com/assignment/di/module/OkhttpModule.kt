package com.assignment.di.module


import com.assignment.util.Constants
import com.assignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val okhttpModule = module {
    single {
        val okHttpBuilder = OkHttpClient()
            .newBuilder()
            .addInterceptor(get<HttpLoggingInterceptor>())

            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(Constants.CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)


        okHttpBuilder.build() as OkHttpClient
    }



    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        httpLoggingInterceptor
    }


}