package com.thingspeak.upw_iot.model

import android.text.TextUtils
import androidx.databinding.BaseObservable

data class LoginUser( var username:String, var password:String):BaseObservable() {

   fun isValidUser():Int{
       if (TextUtils.isEmpty(username)){
           return 1 // username is incorrect
       }else if (TextUtils.isEmpty(password)){
           return 2 // password is incorrect
       }else{
           return -1 // on success
       }

   }

}