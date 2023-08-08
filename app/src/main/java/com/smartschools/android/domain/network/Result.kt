package com.smartschools.android.domain.network

sealed class Result<T>(val data: T?, val errorType: String) {
    class Success<T>(data: T) : Result<T>(data, "null")
    class Loading<T>() : Result<T>(null, "null")
    class Error<T>(errorType: String) : Result<T>(null, errorType)
}