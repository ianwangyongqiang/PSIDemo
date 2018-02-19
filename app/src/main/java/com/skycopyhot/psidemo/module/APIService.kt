package com.skycopyhot.psidemo.module

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yongqiangwang on 17/2/18.
 * APIService
 */
interface APIService {

    @GET("/v1/environment/psi")
    fun getPSIData(
            @Query("date_time") dateTime: String
    ): Single<PSIData>
}