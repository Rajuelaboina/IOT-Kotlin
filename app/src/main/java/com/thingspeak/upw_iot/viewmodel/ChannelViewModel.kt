package com.thingspeak.upw_iot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingspeak.upw_iot.model.Channel
import com.thingspeak.upw_iot.model.Channel_Temp
import com.thingspeak.upw_iot.model.Feed
import com.thingspeak.upw_iot.model.Feed_Temp
import com.thingspeak.upw_iot.model.Feed_Water
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.ui.humidityui.HumidityChannelAdapter
import com.thingspeak.upw_iot.ui.tdsui.ChannelAdapter
import com.thingspeak.upw_iot.ui.tempui.TempChannelAdapter


class ChannelViewModel(private var channelRepo: ChannelRepo) :ViewModel() {
    val channelAdapter: ChannelAdapter
    var feedList = MutableLiveData<List<Feed>>()
    var channelList = MutableLiveData<Channel>()
    val tempchannelAdapter: TempChannelAdapter
    var tempfeedList = MutableLiveData<List<Feed_Temp>>()
    var tempchannelList = MutableLiveData<Channel_Temp>()

   // var phFeedList = MutableLiveData<List<Feed_Water>>()
   // var phChannelList = MutableLiveData<Channel_Water>()

    var hmdtAdapter : HumidityChannelAdapter
    init {
        channelRepo = ChannelRepo()
        channelAdapter = ChannelAdapter()
        tempchannelAdapter = TempChannelAdapter()
        hmdtAdapter = HumidityChannelAdapter()
    }
    fun getAllFeeds():LiveData<List<Feed>>{
        return channelRepo.feedList

    }
    fun getChannel(): LiveData<Channel> {
        channelList = channelRepo.getChannel()
        return channelList
    }

    fun getTempChannel(): LiveData<Channel_Temp> {
        tempchannelList = channelRepo.getTempChannel()
        return tempchannelList
    }

    fun getTempFeeds(): LiveData<List<Feed_Temp>> {
        tempfeedList = channelRepo.getTempFeeds()
        return tempfeedList
    }

    fun setAdapter(it: List<Feed>) {
        channelAdapter.updateList(it)
    }
    fun disChannelAdapter(): ChannelAdapter {
        return channelAdapter
    }
    fun setTempAdapter(it: List<Feed_Temp>) {
        tempchannelAdapter.updateList(it)
    }
    fun tempdisChannelAdapter(): TempChannelAdapter {
        return tempchannelAdapter
    }
    // water mean ph value
    fun getWaterFeedList():LiveData<List<Feed_Water>>{
        return channelRepo.waterFeedList
    }
    fun setPhAdapter(feedWatersList: List<Feed_Water>) {
        hmdtAdapter.updateList(feedWatersList)
    }
    fun hmdtchannelAdapter():HumidityChannelAdapter{
        return hmdtAdapter
    }
}