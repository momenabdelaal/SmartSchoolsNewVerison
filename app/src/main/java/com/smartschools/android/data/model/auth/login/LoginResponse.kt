package com.smartschools.android.data.model.auth.login

data class LoginResponse(
    val message: String,
    val result: ResultModel,
    val status: Boolean
)