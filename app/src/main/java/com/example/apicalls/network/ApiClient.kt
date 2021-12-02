package com.example.apicalls.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL="https://reqres.in/api/users/"
class ApiClient {
    private lateinit var retrofit:Retrofit
    fun getApiClient():Retrofit{
        val gson=GsonBuilder().setLenient().create()
        val okHttpClient=OkHttpClient.Builder().readTimeout(100,TimeUnit.SECONDS).connectTimeout(100,TimeUnit.SECONDS).build()
        retrofit=Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit
    }
}