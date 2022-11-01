package com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson.withretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamentaldicoding2022.data.network.ApiClient
import com.example.androidfundamentaldicoding2022.model.restaurant.CustomerReview
import com.example.androidfundamentaldicoding2022.model.restaurant.ResponseData
import com.example.androidfundamentaldicoding2022.model.restaurant.Restaurant
import com.example.androidfundamentaldicoding2022.model.restaurant.post.PostReviewResponse
import com.example.androidfundamentaldicoding2022.wrapper.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantViewModel : ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant

    private val _listReview = MutableLiveData<List<CustomerReview>>()
    val listReview: LiveData<List<CustomerReview>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    companion object{
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        findRestaurant()
    }

    private fun findRestaurant() {
        _isLoading.value = true

        val client = ApiClient.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _restaurant.value = response.body()?.restaurant!!
                    _listReview.value = response.body()?.restaurant?.customerReviews as List<CustomerReview>?
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun postReview(review: String) {
        _isLoading.value = true
        val client = ApiClient.getApiService().postReview(RESTAURANT_ID, "Dicoding", review)
        client.enqueue(object : Callback<PostReviewResponse> {
            override fun onResponse(call: Call<PostReviewResponse>, response: Response<PostReviewResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviews as List<CustomerReview>?
                    _snackbarText.value = Event(response.body()?.message.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}