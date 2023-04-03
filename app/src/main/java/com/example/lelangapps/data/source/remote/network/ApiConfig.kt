package com.example.lelangapps.data.source.remote.network

import com.example.lelangapps.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private const val BASE_URL = Constants.BASE_URL+"api/"
    private const val URL_API = "http://luckauction.enricko.com/api/"

    private val client : Retrofit
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client : OkHttpClient = OkHttpClient.Builder()
               // .addInterceptor(AuthInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

    val provideApiService : ApiService
        get() = client.create(ApiService::class.java)
}