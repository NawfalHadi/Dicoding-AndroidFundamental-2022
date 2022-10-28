package com.example.androidfundamentaldicoding2022.data.network

import com.example.androidfundamentaldicoding2022.model.restaurant.ResponseData
import com.example.androidfundamentaldicoding2022.model.restaurant.post.PostReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id: String
    ): Call<ResponseData>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): Call<PostReviewResponse>
}