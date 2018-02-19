package com.skycopyhot.psidemo.module

import com.google.gson.annotations.SerializedName

/**
 * Created by yongqiangwang on 17/2/18.
 * PSIData
 */
data class PSIData(
        @SerializedName("api_info")
        val apiInfo: StatusData,
        @SerializedName("region_metadata")
        val regions: List<RegionData>,
        @SerializedName("items")
        val items: List<ItemData>
)