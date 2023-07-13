package com.thingspeak.upw_iot.model

import com.google.gson.annotations.SerializedName

data class Channel_Temp (
        @SerializedName("id")
        val id: Integer,

        @SerializedName("name")
         val name:String,

        @SerializedName("description")

        val description: String ,
        @SerializedName("latitude")
        val latitude: String ,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("field1")
        val field1: String ,
        @SerializedName("field2")
        val field2: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("last_entry_id")
        val lastEntryId:Integer

        )