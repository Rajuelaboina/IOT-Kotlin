package com.thingspeak.upw_iot.listeners

interface LoginCallBack {
    fun onError(code: Int)
    fun onSuccess()
    fun onRefresh()
}