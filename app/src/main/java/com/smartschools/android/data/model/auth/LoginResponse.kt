package com.smartschools.android.data.model.auth

data class LoginResponse(
    val message: String,
    val data: ResultModel,
    val status: Boolean
)