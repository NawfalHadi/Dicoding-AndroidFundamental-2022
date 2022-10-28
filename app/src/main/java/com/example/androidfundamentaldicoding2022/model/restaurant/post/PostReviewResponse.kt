package com.example.androidfundamentaldicoding2022.model.restaurant.post


import com.example.androidfundamentaldicoding2022.model.restaurant.CustomerReview
import com.google.gson.annotations.SerializedName

data class PostReviewResponse(
    @SerializedName("customerReviews")
    val customerReviews: List<CustomerReview?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?
)