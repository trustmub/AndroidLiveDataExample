package com.trusticles.livedataexample.ViewModels

import android.arch.lifecycle.*
import android.content.pm.ApplicationInfo
import com.trusticles.livedataexample.Utilities.Network.ConnectionListener

class ConnectionViewModel(val connectionListenerRepository: ConnectionListener, val context: ApplicationInfo) : ViewModel() {

}