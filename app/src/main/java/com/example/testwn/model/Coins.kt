package com.example.testwn.model


import com.google.gson.annotations.SerializedName

data class Coins(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)