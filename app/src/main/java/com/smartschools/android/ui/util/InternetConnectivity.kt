package com.smartschools.android.ui.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData

class InternetConnectivity  (val context: Context) : LiveData<Boolean>() {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var Connnectserver: Boolean? = false
    val reciveconnctivitymanager = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Connnectserver=false
            UnpdateConnection()
            postValue(Connnectserver)
        }
    }
    init {
        UnpdateConnection()
    }

    fun UnpdateConnection(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities?.run {
                Connnectserver= capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            }
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                Connnectserver = activeNetworkInfo != null && activeNetworkInfo.isConnected
            } catch (e: Exception) {
            }
        }
    }

    override fun onActive() {
        super.onActive()
        context.registerReceiver(
            reciveconnctivitymanager,
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(reciveconnctivitymanager)
    }
}