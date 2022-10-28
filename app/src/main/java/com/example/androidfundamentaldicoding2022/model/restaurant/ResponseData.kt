package com.example.androidfundamentaldicoding2022.model.restaurant


import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("restaurant")
    val restaurant: Restaurant?
)