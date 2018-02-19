package com.skycopyhot.psidemo.logic

import android.content.Context
import com.skycopyhot.psidemo.module.APIService
import com.skycopyhot.psidemo.module.ResultData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yongqiangwang on 17/2/18.
 * LogicRepository
 */
class LogicRepository(private val apiService: APIService, context: Context) : BaseRepository(context), ILogicRepository {

    override fun getPsiData(): Single<ResultData> {
        val dateTime = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
        return translate(apiService.getPSIData(dateTime))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    Single.just(ResultData(it))
                }
    }
}