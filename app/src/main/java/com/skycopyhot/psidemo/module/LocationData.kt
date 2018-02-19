package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * LocationData
 */
data class LocationData(
        @SerializedName("latitude")
        val latitude: Double,
        @SerializedName("longitude")
        val longitude: Double
)