package com.smartschools.android.data.model.auth.login.auth

data class LoginResponse(
    val message: String,
    val result: ResultModel,
    val status: Boolean
)