package com.smartschools.android.core.network

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//        setupActivityListener();

    }

//    private fun setupActivityListener() {
//        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
//            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//                activity.window.setFlags(
//                    WindowManager.LayoutParams.FLAG_SECURE,
//                    WindowManager.LayoutParams.FLAG_SECURE
//                )
//            }
//
//            override fun onActivityStarted(activity: Activity) {}
//            override fun onActivityResumed(activity: Activity) {}
//            override fun onActivityPaused(activity: Activity) {}
//            override fun onActivityStopped(activity: Activity) {}
//            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
//            override fun onActivityDestroyed(activity: Activity) {}
//        })
//    }

    companion object {

        lateinit  var appContext: Context

    }
}