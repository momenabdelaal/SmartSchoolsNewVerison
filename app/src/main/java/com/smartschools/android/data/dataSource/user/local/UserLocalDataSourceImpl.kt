package com.smartschools.android.data.dataSource.user.local

import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferences
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserLocalDataSource {
    override fun getToken() = sharedPreferences.getApiKeyToken()

    override fun getPassword() = sharedPreferences.getUserPassword()

    override fun getRememberMe() = sharedPreferences.getRememberMe()

    override fun setToken(apikeyToken: String) = sharedPreferences.setApiKeyToken(apikeyToken)

    override fun setPassword(password: String) = sharedPreferences.setUserPassword(password)

    override fun setRememberMe(rememberMe: String) = sharedPreferences.setRememberMe(rememberMe)

    override fun clearUserSettings() = sharedPreferences.clearAll()
}