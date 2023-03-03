package com.neotica.restaurantreview

import com.neotica.restaurantreview.response.CustomerReview
import com.neotica.restaurantreview.response.Restaurant
import com.neotica.restaurantreview.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    //Step 8: Create GET method from Retrofit
    @GET("detail/{id}")
    //Step 9: Define get function to call the id of the Response
    fun getRestaurant(
        //Step 10: Define the path of id
        @Path("id") id: String
    //Step 11: Call the Response that has the destination of the id
    ): Call<RestaurantResponse>
    //Step 35: Create POST method
    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id")
        id: String,
        @Field("name")
        name: String,
        @Field("review")
        review: String
    ): Call<Restaurant>
}