package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * ReadingsData
 */
data class ReadingsData(
        @SerializedName("o3_sub_index")
        val o3SubIndex: ReadingData,
        @SerializedName("pm10_twenty_four_hourly")
        val pm10TwentyFourHourly: ReadingData,
        @SerializedName("pm10_sub_index")
        val pm10SubIndex: ReadingData,
        @SerializedName("co_sub_index")
        val coSubIndex: ReadingData,
        @SerializedName("pm25_twenty_four_hourly")
        val pm25TwentyFourHourly: ReadingData,
        @SerializedName("so2_sub_index")
        val so2SubIndex: ReadingData,
        @SerializedName("co_eight_hour_max")
        val coEightHourMax: ReadingData,
        @SerializedName("no2_one_hour_max")
        val no2OneHourMax: ReadingData,
        @SerializedName("so2_twenty_four_hourly")
        val so2TwentyFourHourly: ReadingData,
        @SerializedName("pm25_sub_index")
        val pm25SubIndex: ReadingData,
        @SerializedName("psi_twenty_four_hourly")
        val psiTwentyFourHourly: ReadingData,
        @SerializedName("o3_eight_hour_max")
        val o3EightHourMax: ReadingData
)