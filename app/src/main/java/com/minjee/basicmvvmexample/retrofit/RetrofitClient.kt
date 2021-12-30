package com.minjee.basicmvvmexample.retrofit

import com.minjee.basicmvvmexample.BuildConfig
import com.minjee.basicmvvmexample.services.VajehYabService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val address = "https://api.codebazan.ir/"

    private val retrofitClient: Retrofit.Builder by lazy {
        val levelType: Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            Level.BODY else Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(address)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: VajehYabService by lazy {
        retrofitClient
            .build()
            .create(VajehYabService::class.java)
    }

}