package com.thingspeak.upw_iot.model

data class TDSValues(
    val channel: Channel,
    val feeds: List<Feed>
)