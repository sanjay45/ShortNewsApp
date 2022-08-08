package com.android.example.shortnewsapp.api

import com.android.example.shortnewsapp.models.NewsApiResponse
import com.android.example.shortnewsapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") countryCode: String = "in",
        @Query("page") pageNumber: Int = 1
    ): Response<NewsApiResponse>
}