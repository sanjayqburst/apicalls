package com.example.apicalls.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build

class NetworkStatus(private val context: Context) {
    private val connectivityManager:ConnectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkCallback:ConnectivityManager.NetworkCallback
    private fun networkConnectivityCallBack(): Boolean {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            networkCallback=object: ConnectivityManager.NetworkCallback(){
                override fun onLost(network: Network) {
                    super.onLost(network)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                }
            }
        }
        return false
    }
}