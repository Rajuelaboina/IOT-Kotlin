package com.thingspeak.upw_iot.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class ProgressUtil {
    companion object{
       @SuppressLint("StaticFieldLeak")
       lateinit var  prog: ProgressBar
       fun showProgress(context: Context, linear1: ViewGroup){
           prog =  ProgressBar(context)
           prog.visibility = View.VISIBLE
           linear1.addView(prog)
       }
        fun hideProgress(context: Context){
            prog.visibility = View.GONE
        }
    }
}