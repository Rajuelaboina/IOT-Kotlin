package com.thingspeak.upw_iot.model

import com.google.gson.annotations.SerializedName

data class TempHumidity(
    @SerializedName("channel")
    val channel: Channel_Temp ,

    @SerializedName("feeds")
    val feeds:List<Feed_Temp>


)