package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 18/2/18.
 * StatusData
 */
data class StatusData(
        @SerializedName("status")
        val status: String
)