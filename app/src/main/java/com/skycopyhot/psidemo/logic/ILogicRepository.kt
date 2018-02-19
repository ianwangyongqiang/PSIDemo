package com.skycopyhot.psidemo.logic

import com.skycopyhot.psidemo.module.ResultData
import io.reactivex.Single

/**
 * Created by yongqiangwang on 17/2/18.
 * ILogicRepository
 */
interface ILogicRepository {

    fun getPsiData(): Single<ResultData>
}