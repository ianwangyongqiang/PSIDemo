package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * ItemData
 */
data class ItemData(
        @SerializedName("timestamp")
        val timestamp: String,
        @SerializedName("update_timestamp")
        val updateTimestamp: String,
        @SerializedName("readings")
        val readings: ReadingsData

) {
}