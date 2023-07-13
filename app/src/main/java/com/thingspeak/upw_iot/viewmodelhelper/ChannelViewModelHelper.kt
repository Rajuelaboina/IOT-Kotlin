package com.thingspeak.upw_iot.viewmodelhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.viewmodel.ChannelViewModel

class ChannelViewModelHelper(private var repo: ChannelRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ChannelViewModel::class.java)){
            ChannelViewModel(this.repo) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}