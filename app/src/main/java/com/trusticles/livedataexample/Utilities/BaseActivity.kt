package com.trusticles.livedataexample.Utilities

import android.arch.lifecycle.Observer
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trusticles.livedataexample.Models.ConnectionModels
import com.trusticles.livedataexample.Utilities.Network.ConnectionListener
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val connectionListener = ConnectionListener(applicationContext)
        connectionListener.observe(this, Observer<ConnectionModels> { connection ->
            if (connection?.isConnected!!) {
                when (connection.type) {
                    /** using the Anko Library to simplify toast messages */
                    ConnectivityManager.TYPE_MOBILE -> toast("Mobile Network ON")
                    ConnectivityManager.TYPE_WIFI -> toast("Wifi Network ON")
                    else -> toast("${connection.type} is disconnected")
                }
            } else toast("There is no Connection")

        })

    }

}