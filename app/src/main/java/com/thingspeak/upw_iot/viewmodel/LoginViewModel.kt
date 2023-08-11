package com.thingspeak.upw_iot.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.thingspeak.upw_iot.listeners.LoginCallBack
import com.thingspeak.upw_iot.model.LoginUser

class LoginViewModel(var listener: LoginCallBack): ViewModel() {
       val loginUser = LoginUser("","")

    val usernameTextWatcher: TextWatcher
            get() = object :TextWatcher{
                override fun beforeTextChanged(s: CharSequence?,start: Int,count: Int,after: Int) {
                   listener.onRefresh()
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                  loginUser.username = s.toString().trim()
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
    val passwordTextWatcher: TextWatcher get() = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            listener.onRefresh()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              loginUser.password = s.toString().trim()
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
    fun onButtonClicked(view:View){
        val code = loginUser.isValidUser()
        if (code==1){
              listener.onError(code)
        }else if (code==2){
            listener.onError(code)
        }else{
            listener.onSuccess()
        }
    }

}