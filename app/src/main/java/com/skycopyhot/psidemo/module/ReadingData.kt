package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * ReadingData
 */
data class ReadingData(
        @SerializedName("west")
        val west: Float,
        @SerializedName("national")
        val national: Float,
        @SerializedName("east")
        val east: Float,
        @SerializedName("central")
        val central: Float,
        @SerializedName("south")
        val south: Float,
        @SerializedName("north")
        val north: Float
)