//package com.exas.qpmoemp.core.appUtils
//
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.security.crypto.EncryptedSharedPreferences
//import androidx.security.crypto.MasterKeys
//import com.osul.qdpmapp.core.appUtils.Constants.PUSH_TOKEN
//import com.osul.qdpmapp.core.appUtils.Constants.USER_LOGIN
//import com.smartschools.android.core.network.MyApplication.Companion.instance
//
//
//class SharedPrefUtils {
//
//    var masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//
//     var mSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
//        USER_LOGIN,
//        masterKeyAlias,
//        instance?.applicationContext!!,
//        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//
//    private var mSharedPreferencesEditor: SharedPreferences.Editor = mSharedPreferences.edit()
//
//    init {
//        mSharedPreferencesEditor.apply()
//    }
//
//    fun setValue(key: String, value: Any?) {
//        when (value) {
//            is Int? -> {
//                mSharedPreferencesEditor.putInt(key, value!!)
//                mSharedPreferencesEditor.apply()
//            }
//            is String? -> {
//                mSharedPreferencesEditor.putString(key, value!!)
//                mSharedPreferencesEditor.apply()
//            }
//            is Double? -> {
//                mSharedPreferencesEditor.putString(key, value.toString())
//                mSharedPreferencesEditor.apply()
//            }
//            is Long? -> {
//                mSharedPreferencesEditor.putLong(key, value!!)
//                mSharedPreferencesEditor.apply()
//            }
//            is Boolean? -> {
//                mSharedPreferencesEditor.putBoolean(key, value!!)
//                mSharedPreferencesEditor.apply()
//            }
//        }
//    }
//
//    fun getStringValue(key: String, defaultValue: String = ""): String {
//        return mSharedPreferences.getString(key, defaultValue)!!
//    }
//
//    fun getIntValue(key: String, defaultValue: Int): Int {
//        return mSharedPreferences.getInt(key, defaultValue)
//    }
//
//    fun getLongValue(key: String, defaultValue: Long): Long {
//        return mSharedPreferences.getLong(key, defaultValue)
//    }
//
//    fun getBooleanValue(keyFlag: String, defaultValue: Boolean = false): Boolean {
//        return mSharedPreferences.getBoolean(keyFlag, defaultValue)
//    }
//
//    fun removeKey(key: String) {
//        mSharedPreferencesEditor.remove(key)
//        mSharedPreferencesEditor.apply()
//    }
//
// /*   fun saveUserProfile(userProfile: LoginResponse?) {
//
//        val gson = Gson()
//        val json = gson.toJson(userProfile)
//        mSharedPreferencesEditor.putString(USER_LOGIN, json)
//        mSharedPreferencesEditor.apply()
//    }
//
//    fun getUserProfile(): LoginResponse? {
//        val gson = Gson()
//        val json: String = mSharedPreferences.getString(USER_LOGIN, "")!!
//        return if (json == "") null else gson.fromJson(json, LoginResponse::class.java)
//    }*/
//
//    fun clear() {
//
//        mSharedPreferencesEditor.clear().apply()
//      }
//
//    // push token
//    fun setPushToken(context: Context, pushToken: String?) {
//        val pref = context.getSharedPreferences(PUSH_TOKEN, Context.MODE_PRIVATE)
//        val editor = pref.edit()
//        editor.putString("PUSH_TOKEN", pushToken)
//        editor.apply()
//    }
//
//    fun getPushToken(context: Context) : String? {
//        val pref = context.getSharedPreferences(PUSH_TOKEN, Context.MODE_PRIVATE)
//        return pref.getString("PUSH_TOKEN", "")
//    }
//
//    fun clearPushToken(context: Context) {
//        val pref = context.getSharedPreferences(PUSH_TOKEN, Context.MODE_PRIVATE)
//        pref.edit().clear().apply()
//    }
///*    fun clearAll(context: Context) {
//            clearPushToken(context)
//    }*/
//
//}