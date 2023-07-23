package com.exas.qpmoemp.domain.network

import com.smartschools.android.domain.network.ErrorType

sealed class Result<T>(val data: T?, val errorType: ErrorType?) {
    class Success<T>(data: T) : Result<T>(data, null)
    class Loading<T>() : Result<T>(null, null)
    class Error<T>(errorType: ErrorType?) : Result<T>(null, errorType)
}