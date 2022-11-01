package com.example.androidfundamentaldicoding2022.ui.webapiandparsingjson.withretrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidfundamentaldicoding2022.R
import com.example.androidfundamentaldicoding2022.data.network.ApiClient
import com.example.androidfundamentaldicoding2022.databinding.ActivityRestaurantBinding
import com.example.androidfundamentaldicoding2022.model.restaurant.CustomerReview
import com.example.androidfundamentaldicoding2022.model.restaurant.ResponseData
import com.example.androidfundamentaldicoding2022.model.restaurant.Restaurant
import com.example.androidfundamentaldicoding2022.model.restaurant.post.PostReviewResponse
import cz.msebera.android.httpclient.protocol.ResponseDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    private val viewModel : RestaurantViewModel by viewModels()

    companion object {
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.restaurant.observe(this) {
            setRestaurantData(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        viewModel.listReview.observe(this) {
            setReviewData(it)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.btnSend.setOnClickListener { view ->
            viewModel.postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



    private fun setReviewData(customerReviews: List<CustomerReview?>?) {
        val listReview = ArrayList<String>()
        for (review in customerReviews!!) {
            listReview.add(
                """
                ${review?.review}
                - ${review?.name}
                """.trimIndent()
            )
        }

        val adapter = ReviewAdapter(listReview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun setRestaurantData(restaurant: Restaurant?) {
        binding.tvTitle.text = restaurant?.name
        binding.tvDescription.text = restaurant?.description
        Glide.with(this)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant?.pictureId}")
            .into(binding.ivPicture)
    }

    private fun showLoading(b: Boolean) {
        print(b)
    }
}