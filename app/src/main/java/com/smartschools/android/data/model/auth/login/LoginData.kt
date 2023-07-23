package com.smartschools.android.data.model.auth.login

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginData(
    val Lang: String,
    val Phone: String,
    val UserID: Int,
    val token: String
)