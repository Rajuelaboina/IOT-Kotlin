package com.thingspeak.upw_iot.model

data class Home_Model(
    val channel: ChannelX,
    val feeds: List<FeedX>
)