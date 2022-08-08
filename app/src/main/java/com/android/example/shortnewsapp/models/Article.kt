package com.android.example.shortnewsapp.models

import com.google.gson.annotations.SerializedName

data class Article(
    val author: String,
    val title: String,
    @SerializedName(value = "urlToImage")
    val imageUrl: String
)
