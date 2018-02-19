package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * RegionData
 */
data class RegionData(
        @SerializedName("name")
        val name: String,
        @SerializedName("label_location")
        val location: LocationData
)