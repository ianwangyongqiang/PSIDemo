package com.skycopyhot.psidemo.module

import android.content.Context
import com.skycopyhot.psidemo.R

/**
 * Created by yongqiangwang on 17/2/18.
 * ResultData
 */
class ResultData(private val psiData: PSIData) {

    fun getRegions(): List<RegionData> = psiData.regions

    fun getData(context: Context, location: ELocation): String {
        val readingsData = psiData.items[0].readings
        return String.format(context.getString(R.string.result_reading),
                getReading(location, readingsData.o3SubIndex),
                getReading(location, readingsData.pm10SubIndex),
                getReading(location, readingsData.pm10TwentyFourHourly),
                getReading(location, readingsData.coSubIndex),
                getReading(location, readingsData.pm25TwentyFourHourly),
                getReading(location, readingsData.so2SubIndex),
                getReading(location, readingsData.coEightHourMax),
                getReading(location, readingsData.no2OneHourMax),
                getReading(location, readingsData.so2TwentyFourHourly),
                getReading(location, readingsData.pm25SubIndex),
                getReading(location, readingsData.psiTwentyFourHourly),
                getReading(location, readingsData.o3EightHourMax))
    }

    private fun getReading(location: ELocation, readingData: ReadingData): Float {
        return when (location) {
            ELocation.WEST -> readingData.west
            ELocation.CENTRAL -> readingData.central
            ELocation.EAST -> readingData.east
            ELocation.NATIONAL -> readingData.national
            ELocation.NORTH -> readingData.north
            ELocation.SOUTH -> readingData.south
        }
    }
}