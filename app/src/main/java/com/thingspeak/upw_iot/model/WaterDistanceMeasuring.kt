package com.thingspeak.upw_iot.model

data class WaterDistanceMeasuring(
    val channel: Channel_Water,
    val feeds: List<Feed_Water>
)