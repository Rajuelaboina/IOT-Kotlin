package com.thingspeak.upw_iot.model

import java.util.Comparator

data class Feed_Water(
    val created_at: String,
    val entry_id: Int,
    val field1: String,
    val field2: String,
    val field3: Any
)