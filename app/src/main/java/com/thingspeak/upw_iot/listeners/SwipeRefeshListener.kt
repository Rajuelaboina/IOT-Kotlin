package com.thingspeak.upw_iot.listeners

interface SwipeRefreshListener {
    fun onRefreshListener()
    companion object{
        lateinit var refreshListener: SwipeRefreshListener
        fun onSwipeRefreshListener(refreshListener: SwipeRefreshListener){
            this.refreshListener =refreshListener
        }
    }
}