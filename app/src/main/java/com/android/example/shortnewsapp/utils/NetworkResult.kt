package com.android.example.shortnewsapp.utils

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data = data)
    class Error<T>(data: T? = null, errorMessage: String?) : NetworkResult<T>(data, errorMessage)
    class NetworkError<T>: NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
}
