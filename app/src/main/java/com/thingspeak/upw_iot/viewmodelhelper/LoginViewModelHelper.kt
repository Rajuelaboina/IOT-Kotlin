package com.thingspeak.upw_iot.viewmodelhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.thingspeak.upw_iot.listeners.LoginCallBack
import com.thingspeak.upw_iot.viewmodel.LoginViewModel

class LoginViewModelHelper(var listener: LoginCallBack):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return LoginViewModel(listener) as T
    }
}