package com.neotica.restaurantreview

import com.neotica.restaurantreview.response.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Step 8: Create GET method from Retrofit
    @GET("detail/{id}")
    //Step 9: Define get function to call the id of the Response
    fun getRestaurant(
        //Step 10: Define the path of id
        @Path("id") id: String
    //Step 11: Call the Response that has the destination of the id
    ): Call<Restaurant>
}