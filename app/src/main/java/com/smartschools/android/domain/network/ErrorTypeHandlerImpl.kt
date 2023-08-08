package com.smartschools.android.domain.network

import android.util.Log
import com.smartschools.android.domain.network.ErrorType
import com.smartschools.android.domain.network.ErrorTypeHandler
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorTypeHandlerImpl @Inject constructor() : ErrorTypeHandler {
    override fun getError(exception: Exception): ErrorType {
        return when (exception) {
            is UnknownHostException -> {
                ErrorType.NetworkError
            }
            is SocketTimeoutException ->{
                ErrorType.UnknownError
            }

            else -> {
                Log.d("error", "getError: "+exception.message)
                ErrorType.UnknownError
            }
        }
    }
}