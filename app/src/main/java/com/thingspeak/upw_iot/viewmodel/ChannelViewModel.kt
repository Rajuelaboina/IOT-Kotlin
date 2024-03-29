package com.thingspeak.upw_iot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingspeak.upw_iot.model.*
import com.thingspeak.upw_iot.repo.ChannelRepo
import com.thingspeak.upw_iot.ui.humidityui.HumidityChannelAdapter
import com.thingspeak.upw_iot.ui.phui.PhChannelAdapter
import com.thingspeak.upw_iot.ui.tdsui.ChannelAdapter
import com.thingspeak.upw_iot.ui.tempui.TempChannelAdapter
import com.thingspeak.upw_iot.ui.waterui.WaterDisChannelAdapter


class ChannelViewModel(private var channelRepo: ChannelRepo) :ViewModel() {
    private val channelAdapter: ChannelAdapter
    private val tempchannelAdapter: TempChannelAdapter
    private var waterAdapter: WaterDisChannelAdapter

    private var hmdtAdapter : HumidityChannelAdapter
    //ph adapter
    private var phvaluesAdapter: PhChannelAdapter
    init {
        channelRepo = ChannelRepo()
        channelAdapter = ChannelAdapter()
        tempchannelAdapter = TempChannelAdapter()
        hmdtAdapter = HumidityChannelAdapter()
        phvaluesAdapter= PhChannelAdapter()
        waterAdapter = WaterDisChannelAdapter()
    }
    fun getAllFeeds():MutableLiveData<List<Feed>>{
        channelRepo.getAllData()
        return channelRepo.feedList

    }
    fun getChannel(): MutableLiveData<Channel> {
        channelRepo.getAllData()
       // channelList = channelRepo.getChannel()
        return  channelRepo.getChannel()
    }

    fun getTempChannel(): MutableLiveData<Channel_Temp> {
        channelRepo.getTempHumidity()
      //  tempchannelList = channelRepo.getTempChannel()
        return channelRepo.getTempChannel()
    }
      //temp and humidity
    fun getTempFeeds(): MutableLiveData<List<Feed_Temp>> {
        channelRepo.getTempHumidity()
       // tempfeedList = channelRepo.getTempFeeds()
        return  channelRepo.getTempFeeds()
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
    fun getWaterFeedList():MutableLiveData<List<Feed_Water>>{
        channelRepo.getWaterValues()
        return channelRepo.waterFeedList
    }
    fun setPhAdapter(feedWatersList: List<Feed_Water>) {
        waterAdapter.updateList(feedWatersList)
    }
    fun hmdtchannelAdapter():WaterDisChannelAdapter{
        return waterAdapter
    }
    //ph values
    fun getPhFeedList():MutableLiveData<List<Feed_ph>>{
        channelRepo.getPhValues()
        return channelRepo.phFeedList
    }
    fun setPhVlauesAdapter(feedPhList: List<Feed_ph>) {
        phvaluesAdapter.updateList(feedPhList)
    }
    fun phValuesAdapter():PhChannelAdapter{
        return phvaluesAdapter
    }
    //humidity
    fun setHumidityAdapter(feedWatersList: List<Feed_Temp>){
        hmdtAdapter.updateList(feedWatersList)
    }
    fun humidityAdapter():HumidityChannelAdapter{
        return hmdtAdapter
    }

    fun getHomeChannel(): MutableLiveData<ChannelX> {
        channelRepo.getHomeAllData()
        return channelRepo.homeChannel
    }

    fun getHomeFeedList(): MutableLiveData<List<FeedX>> {
        channelRepo.getHomeAllData()
        return channelRepo.homeFeedList
    }


}