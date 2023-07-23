package com.smartschools.android.data.dataSource.user.local

interface UserLocalDataSource {

    fun getToken(): String
    fun getPassword(): String
    fun getRememberMe(): String

    fun setToken(apiKeyToken: String)
    fun setPassword(password: String)
    fun setRememberMe(rememberMe: String)

    fun clearUserSettings()
}