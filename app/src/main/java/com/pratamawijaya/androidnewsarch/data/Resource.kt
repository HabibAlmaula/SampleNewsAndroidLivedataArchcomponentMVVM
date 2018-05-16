package com.pratamawijaya.androidnewsarch.data

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Error<out T>(val errorMsg: String) : Resource<T>()
}