package com.neotica.restaurantreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.neotica.restaurantreview.databinding.ActivityMainBinding
import com.neotica.restaurantreview.response.CustomerReview
import com.neotica.restaurantreview.response.Restaurant
import com.neotica.restaurantreview.response.RestaurantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Step 22: Create a new companion object containing TAG and ID
    companion object {
        private const val TAG = "MainActivity"
        private const val ID = "uewq1zg2zlskfw1e867"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recView()
        findRestaurant()
    }

    //Step 21: Define the logic of RecyclerView in the MainActivity
    private fun recView() {
        //Step 21.1: Define layoutManager
        val layoutManager = LinearLayoutManager(this)
        //Step 21.2 Bind layout manager to recview
        binding.rvReview.layoutManager = layoutManager
        //Step 21.3 Define item divider
        val itemDivider = DividerItemDecoration(this, layoutManager.orientation)
        //Step 21.4 Bind the item divider to recview
        binding.rvReview.addItemDecoration(itemDivider)
    }

    //Step 23: Setup showLoading function containing progressbar visibility
    //Define the condition of loading as boolean inside the primary parameter
    private fun showLoading(isLoading: Boolean) {
        //Step 24: Declare the condition
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    //Step 25: Setup Review Data
    //Call the intended list into the primary parameter.
    private fun setReviewData(consumerReview: List<CustomerReview>){
        val listReview = ArrayList<String>()
        //Step 26: Define a for loop and iterate object in the list.
        for (review in consumerReview) {
            //Step 27: Add to arraylist and define the path.
            listReview.add(
                """
                    ${review.review}
                    - ${review.name}
                """.trimIndent()
            )
        }
        //Step 28: Setup review adapter.
        val adapter = ReviewAdapter(listReview)
        binding.apply {
            rvReview.adapter = adapter
            edReview.setText("")
        }
    }
    //Step 29: Setup Restaurant Data
    private fun setRestaurantData(restaurant: Restaurant){
        binding.apply {
            tvTitle.text = restaurant.name
            tvDescription.text = restaurant.description
            Glide.with(this@MainActivity)
                .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
                .into(ivPicture)
        }
    }
    //Step 30: Setup main retrofit function
    private fun findRestaurant(){
        showLoading(true)
        //Step 31: Define client id.
        val client = ApiConfig.getApiService().getRestaurant(ID)
        //Step 32: Enqueue client, callback to response.
        client.enqueue(object : Callback<RestaurantResponse>{
            //Step 33 Implement members
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody!=null){
                        setRestaurantData(responseBody.restaurant)
                        setReviewData(responseBody.restaurant.customerReviews)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG,"onFailure: ${t.message}")
            }


        })
    }
}