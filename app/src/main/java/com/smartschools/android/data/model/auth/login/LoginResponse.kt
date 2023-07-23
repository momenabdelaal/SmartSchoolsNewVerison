package com.smartschools.android.data.model.auth.login

import androidx.annotation.Keep
import com.smartschools.android.data.model.auth.login.LoginData

@Keep
data class LoginResponse(
    val Code: Int,
    val Success: Boolean,
    val `data`: LoginData
)