package com.neotica.restaurantreview.response

data class RestaurantResponse(
    val error: Boolean,
    val message: String,
    val restaurant: Restaurant
)