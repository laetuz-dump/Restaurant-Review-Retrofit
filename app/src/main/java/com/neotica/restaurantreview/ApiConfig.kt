package com.neotica.restaurantreview

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

class ApiConfig {
    //Step 13: Create a companion object
    companion object{
        //Step 14: Define a function that inherits from ApiService in Retrofit.
        fun getApiService(): ApiService {
            //Step 15: Create logging interceptor.
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            //Step 16: Define OkHttp client
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            //Step 17: Define Retrofit builder
            val retrofit = Retrofit.Builder()
                .baseUrl("https://restaurant-api.dicoding.dev/")
                .client(client)
                .build()
            //Step 18: Return ApiService
            return retrofit.create(ApiService::class.java)
        }
    }
}