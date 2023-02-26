package com.neotica.restaurantreview

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    //Step 13: Create a companion object
    companion object{
        //Step 14: Define a function that inherits from ApiService in Retrofit.
        fun getApiService(): ApiService {
            //Step 15: Create logging interceptor.
            //Step 19: Create security condition
            val loggingInterceptor = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            //Step 16: Define OkHttp client
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            //Step 17: Define Retrofit builder
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            //Step 18: Return ApiService
            return retrofit.create(ApiService::class.java)
        }
    }
}