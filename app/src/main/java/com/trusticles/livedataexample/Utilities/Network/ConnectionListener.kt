package com.trusticles.livedataexample.Utilities.Network

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.trusticles.livedataexample.Models.ConnectionModels

class ConnectionListener(private val context: Context) : LiveData<ConnectionModels>() {

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.extras != null) {
                val activeNetwork: NetworkInfo = intent.extras.get(ConnectivityManager.EXTRA_NETWORK_INFO) as NetworkInfo
                val isConnected = activeNetwork.isConnectedOrConnecting
                if (isConnected) {
                    when (activeNetwork.type) {
                        ConnectivityManager.TYPE_WIFI -> postValue(ConnectionModels(ConnectivityManager.TYPE_WIFI, true))
                        ConnectivityManager.TYPE_MOBILE -> postValue(ConnectionModels(ConnectivityManager.TYPE_MOBILE, true))
                    }
                } else {
                    postValue(ConnectionModels(0, false))
                }

            }
        }

    }

}