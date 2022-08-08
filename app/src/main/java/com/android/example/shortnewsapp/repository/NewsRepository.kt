package com.android.example.shortnewsapp.repository

import android.content.Context
import com.android.example.shortnewsapp.api.NewsApi
import com.android.example.shortnewsapp.models.Article
import com.android.example.shortnewsapp.utils.NetworkResult
import com.android.example.shortnewsapp.utils.NetworkUtils
import com.android.example.shortnewsapp.utils.dispatcher.DispatcherProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val newsApi: NewsApi,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getNews(): NetworkResult<List<Article>> {
        return if (NetworkUtils.isInternetAvailable(applicationContext)) {
            withContext(dispatcherProvider.background) {
                try {
                    val response = newsApi.getNews()
                    if (response.body() != null) {
                        NetworkResult.Success(data = response.body()!!.articles)
                    } else {
                        NetworkResult.Error(errorMessage = "Some Api error occurred")
                    }
                } catch (exception: Exception) {
                    NetworkResult.Error(errorMessage = exception.message)
                }
            }
        } else {
            NetworkResult.NetworkError()
        }
    }
}