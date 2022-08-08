package com.android.example.shortnewsapp.models

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)