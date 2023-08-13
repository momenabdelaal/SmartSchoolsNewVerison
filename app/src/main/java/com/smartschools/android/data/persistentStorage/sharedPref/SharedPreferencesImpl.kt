package com.smartschools.android.data.persistentStorage.sharedPref


import android.content.Context
import com.smartschools.android.core.appUtils.SecurePreferences
import com.smartschools.android.data.model.auth.ResultModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPreferencesImpl @Inject constructor(@ApplicationContext context: Context) :
    SharedPreferences {

    private val sharedPreferencesFileName = "SETTINGS"
    private val userApikeyToken = "USER_API_KEY_TOKEN_SHARED_PREFERENCES"
    private val userIdPerf = "USER_ID_SHARED_PREFERENCES"
    private val isManagerPref = "IS_MANAGER_E_IN_SHARED_PREFERENCES"
    private val isFirstLaunch = "IS_FIRST_LAUNCH"
    private val id = "ID"
    private val mobile = "MOBILE"
    private val name = "NAME"
    private val photo = "PHOTO"
    private val type = "type"
    private val schoolName = "SCHOOL_NAME"
    private val must_change_password = "must_change_password"
    private val must_complete_profile = "must_complete_profile"



    private val userPasswordInSharedPreferences = "USER_PASSWORD_IN_SHARED_PREFERENCES"
    private val rememberMeInSharedPreferences = "REMEMBER_ME_IN_SHARED_PREFERENCES"
    private val countNotificationInSharedPreferences = "COUNT_NOTIFICAITON_IN_SHARED_PREFERENCES"
    private val nameInSharedPreferences = "NAME_IN_SHARED_PREFERENCES"
    private val imageInSharedPreferences = "IMAGE_IN_SHARED_PREFERENCES"
    private val languageInSharedPreferences = "LANG_IN_SHARED_PREFERENCES"
    //private val prefs = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

    private val prefs = SecurePreferences(context,sharedPreferencesFileName,"PMO",true)




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

    override fun isFirstLaunch(isFirst: String) {
        prefs.put(isFirstLaunch, isFirst)
    }

    override fun getFirstLaunch(): String {
        return prefs.getString(isFirstLaunch) ?:"true"
    }

    override fun setSchoolName(nameSchool :String) {
        prefs.put(schoolName, nameSchool)
    }

    override fun getSchoolName(): String {
        return prefs.getString(schoolName) ?:""
    }

    fun saveAllData(model: ResultModel){

        prefs.put(id, model.id.toString())
        prefs.put(userApikeyToken, model.token)
        prefs.put(userApikeyToken, model.token)
        prefs.put(must_change_password, model.must_change_password.toString())
        prefs.put(must_complete_profile, model.must_change_password.toString())
        prefs.put(this.name, model.name)
        prefs.put(photo, model.photo.toString())
        prefs.put(type, model.type)

    }

    fun getAllData(): ResultModel {
        return ResultModel(
            prefs.getString(id)?.toInt() ?: 0,
            prefs.getString(mobile) ?: "",
            prefs.getString(must_change_password)?.toInt() ?: 0,
            prefs.getString(must_complete_profile)?.toInt() ?: 0,
            prefs.getString(name) ?: "",
            prefs.getString(photo) ?: "",
            null,
            prefs.getString(userApikeyToken) ?: "",
            prefs.getString(type) ?: ""
        )
    }

    override fun clearAll() {
        prefs.clear()
        prefs.put(isFirstLaunch, "false")
    }

}