package com.smartschools.android.core.appUtils

import android.annotation.SuppressLint


@SuppressLint("NewApi")
object Constants {



    const val SPLASH_DISPLAY_TIME:Long = 4000

    //RequestsCodes
    var FILE_TYPE_IMAGE = 1001
    var FILE_TYPE_PDF = 1002
    var FILE_TYPE_AUDIO = 1003
    const val SUCCESS = 200
    const val FAILED = 401
    const val NOT_AUTOIZE = 403
    const val NOT_ACCEPTABLE = 406
    const val NOT_FOUND = 404
    const val NOT_ACTIVE = 405
    const val UNKNOWN_ERROR_SERVER = 500


    const val PERMISSION_STORAGE_IMAGES = 1315
    const val CHOOSE_FILE_REQUEST = 9544
    const val CAMERA_REQUEST = 1888
    const val MY_CAMERA_PERMISSION_CODE = 100
    const val VERSION_APIS = 1
    const val CULTURE = "ar-sa"
    var isHomeStatus = false
    var isFavoriteStatus = false
    var isErrorFire = false
    const val INTENT_PAGE = "fragment_name"
    const val INTENT_BUNDLE = "bundle_name"
    const val PHONE = "phone"
    const val CHECK = "check"

    //Page
    const val LOGIN_PAGE = 5
    const val CONFIRM_LOGIN_PAGE = 6
    const val HOME_PAGE = 7
    const val FORGET_PASSWORD_PAGE = 9

    //LANGUAGE
    const val LANGUAGE_ENGLISH = "en-sa"
    const val LANGUAGE_ARABIC = "ar-sa"
    const val UserType= "user_type"

    const val IS_BACK_KEY_PRESSED = "IS_BACK_KEY_PRESSED"

    const val SITE_KEY = "6LddIywmAAAAAAyXKyE2taAC_-IUYby-RJxtX6sB"
    const val SECRET_KEY = "6LddIywmAAAAAP-j8Llno-yQFfrVwOWQGz702q6_"


    //sharedPref
    const val IS_LOGIN = "is_login"
    const val PUSH_TOKEN = "PUSH_TOKEN"
    const val USER_LOGIN = "user_login"

    const val MAIL_ID ="MAIL_ID"
    const val MAIL_PROJECT_ID = "MAIL_PROJECT_ID"
    const val MAIL_PROJECT_NAME = "MAIL_PROJECT_NAME"
    const val MAIL_MESSAGE = "MAIL_MESSAGE"
    const val MAIL_MESSAGE_TITLE = "MAIL_MESSAGE_TITLE"
    const val MAIL_DATE = "MAIL_DATA"
    val densities: ArrayList<Float> = object : ArrayList<Float>() {
        init {
            add(0.75f)
            add(1.0f)
            add(1.5f)
            add(2.0f)
            add(3.0f)
            add(4.0f)
        }
    }


}