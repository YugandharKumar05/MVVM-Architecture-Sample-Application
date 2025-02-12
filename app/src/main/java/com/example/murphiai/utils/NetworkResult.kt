package com.example.murphiai.utils

sealed class NetworkResult<out T> {
    class Loading<T> : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val message: String?) : NetworkResult<T>()
}
