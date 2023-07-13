package com.thingspeak.upw_iot.model

data class Channel_Water(
    val created_at: String,
    val field1: String,
    val field2: String,
    val field3: String,
    val id: Int,
    val last_entry_id: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val updated_at: String
)