package com.skycopyhot.psidemo.logic

import android.content.Context
import com.skycopyhot.psidemo.R
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

/**
 * Created by yongqiangwang on 17/2/18.
 * BaseRepository
 */
abstract class BaseRepository(private val context: Context) {

    protected fun <T> translate(api: Single<T>): Single<T> {
        return Single.create {
            api.subscribeOn(Schedulers.io())
                    .subscribe { t1, t2 ->
                        if (t2 != null) {
                            if (t2 is UnknownHostException) {
                                it.onError(Throwable(context.getString(R.string.no_network)))
                            } else {
                                it.onError(t2)
                            }
                        } else {
                            it.onSuccess(t1)
                        }
                    }
        }
    }
}