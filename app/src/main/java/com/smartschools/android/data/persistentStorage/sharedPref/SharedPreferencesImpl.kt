package com.exas.qpmoemp.data.persistentStorage.sharedPref


import android.content.Context
import com.smartschools.android.core.appUtils.SecurePreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPreferencesImpl @Inject constructor(@ApplicationContext context: Context) :
    SharedPreferences {

    private val sharedPreferencesFileName = "SETTINGS"
    private val userApikeyToken = "USER_API_KEY_TOKEN_SHARED_PREFERENCES"
    private val userIdPerf = "USER_ID_SHARED_PREFERENCES"
    private val isManagerPref = "IS_MANAGER_E_IN_SHARED_PREFERENCES"

    private val userPasswordInSharedPreferences = "USER_PASSWORD_IN_SHARED_PREFERENCES"
    private val rememberMeInSharedPreferences = "REMEMBER_ME_IN_SHARED_PREFERENCES"
    private val countNotificationInSharedPreferences = "COUNT_NOTIFICAITON_IN_SHARED_PREFERENCES"
    private val nameInSharedPreferences = "NAME_IN_SHARED_PREFERENCES"
    private val imageInSharedPreferences = "IMAGE_IN_SHARED_PREFERENCES"
    private val languageInSharedPreferences = "LANG_IN_SHARED_PREFERENCES"
    //private val prefs = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

    private val prefs = SecurePreferences(context,sharedPreferencesFileName,"PMO",true)


//    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//
//    private val prefs =
//        EncryptedSharedPreferences.create(
//            sharedPreferencesFileName,masterKeyAlias ,context,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
//    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
////    private val prefs = EncryptedSharedPreferences.create(
////        // passing a file name to share a preferences
////        sharedPreferencesFileName,
////        masterKeyAlias,
////        context,
////        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
////        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
////    )
//
//
//
////    val mainKey = MasterKeys.Builder(context)
////        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
////        .build()
//
//    val sharedPreferences =

//    with (sharedPreferences.edit()) {
//        // Edit the user's shared preferences...
//        apply()
//    }

    override fun getApiKeyToken(): String {
        return prefs.getString(userApikeyToken) ?: ""
    }



    override fun getUserPassword(): String {
        return prefs.getString(userPasswordInSharedPreferences) ?: ""
    }

    override fun getRememberMe(): String {
        return prefs.getString(rememberMeInSharedPreferences) ?:""
    }

    override fun getNotificationCount(): String {
        return prefs.getString(countNotificationInSharedPreferences) ?:""
    }


    override fun getLanguage(): String {
        return prefs.getString(languageInSharedPreferences) ?: ""
    }

    override fun setApiKeyToken(token: String) {
        prefs.put(userApikeyToken, token)
    }



    override fun setUserPassword(password: String) {
        prefs.put(userPasswordInSharedPreferences, password)
    }

    override fun setRememberMe(rememberMe: String) {
        prefs.put(rememberMeInSharedPreferences, rememberMe)
    }

    override fun setUserId(userId: String) {
        prefs.put(userIdPerf, userId)
    }

    override fun getUserId() : String {
        return prefs.getString(userIdPerf) ?:""
    }

    override fun setIsManager(isManager: String) {
        prefs.put(isManagerPref, isManager)

    }

    override fun getIsManager() : String {
        return prefs.getString(isManagerPref) ?:""

    }

    override fun setNotificationCount(notificationCount: String) {
        prefs.put(countNotificationInSharedPreferences, notificationCount)
    }



    override fun setLanguage(language: String) {
        prefs.put(languageInSharedPreferences, language)
    }


    override fun clearAll() {
        prefs.clear()
    }

}