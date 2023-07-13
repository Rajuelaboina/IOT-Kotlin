package com.thingspeak.upw_iot.model

data class Channel(
    val created_at: String,
    val description: String,
    val field1: String,
    val id: Int,
    val last_entry_id: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val updated_at: String
)