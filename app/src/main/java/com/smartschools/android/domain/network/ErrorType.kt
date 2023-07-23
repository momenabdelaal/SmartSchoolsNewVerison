package com.smartschools.android.domain.network

sealed class ErrorType {
    object NetworkError : ErrorType()
    object ServerError : ErrorType()
    object UnknownError : ErrorType()
    object DataError : ErrorType()
}