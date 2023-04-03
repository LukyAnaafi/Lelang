package com.example.lelangapps.data.source.remote.network

import com.example.lelangapps.util.Prefs
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor:Interceptor {
    val token = Prefs.token
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
            it.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}
