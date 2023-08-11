package com.thingspeak.upw_iot.model

data class PhValues(
    val channel: Channel_Ph,
    val feeds: List<Feed_ph>
)