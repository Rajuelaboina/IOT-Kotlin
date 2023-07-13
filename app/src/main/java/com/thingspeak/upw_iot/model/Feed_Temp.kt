package com.thingspeak.upw_iot.model

import com.google.gson.annotations.SerializedName

data class Feed_Temp (
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("entry_id")
    val entryId: Integer,
    @SerializedName("field1")
     val field1: String ,
    @SerializedName("field2")
    val field2: String

)