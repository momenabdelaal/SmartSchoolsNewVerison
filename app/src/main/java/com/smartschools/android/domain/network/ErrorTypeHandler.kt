package com.smartschools.android.domain.network



interface ErrorTypeHandler {
    fun getError(exception: Exception): ErrorType
}