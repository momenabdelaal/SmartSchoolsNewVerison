package com.smartschools.android.data.network

import android.util.Log
import com.smartschools.android.domain.network.ErrorTypeHandler
import com.smartschools.android.domain.network.Result
import retrofit2.Response
import javax.inject.Inject

class RequestApiCall @Inject constructor(
    private val errorTypeHandler: ErrorTypeHandler
) {
    suspend fun <T : Any> requestApiCall(requestApi: suspend () -> Response<T>): Result<T> {
        return try {
            val response = requestApi.invoke()
            parseApiResponse(response)
        } catch (exception: Exception) {
            Result.Error(exception.message.toString())
        }
    }

    private fun <T> parseApiResponse(response: Response<T>): Result<T> {
        try {
            if (response.isSuccessful) {
                response.body()?.let { apiRes ->
                    return Result.Success(apiRes)
                }
            }
            Log.d("test", "parseApiResponse: "+response.code())
            return Result.Error(response.code().toString())
        } catch (e: Exception) {
            return Result.Error(e.message.toString())
        }
    }
}