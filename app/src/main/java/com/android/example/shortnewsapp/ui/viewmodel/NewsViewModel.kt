package com.android.example.shortnewsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.shortnewsapp.models.Article
import com.android.example.shortnewsapp.repository.NewsRepository
import com.android.example.shortnewsapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val _newsListLiveData = MutableLiveData<NetworkResult<List<Article>>>()
    val newsListLiveData: LiveData<NetworkResult<List<Article>>>
    get() = _newsListLiveData

    private val _articleLiveData = MutableLiveData<Article>()
    val articleLiveData: LiveData<Article>
        get() = _articleLiveData

    fun getNews() {
        _newsListLiveData.value = NetworkResult.Loading()
        viewModelScope.launch {
            _newsListLiveData.postValue(newsRepository.getNews())
        }
    }

    fun setArticle(article: Article) {
        _articleLiveData.value = article
    }
}
