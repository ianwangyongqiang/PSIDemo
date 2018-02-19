package com.skycopyhot.psidemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.skycopyhot.psidemo.PDApplication
import com.skycopyhot.psidemo.logic.ILogicRepository
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by yongqiangwang on 17/2/18.
 * BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    private val disposableContainer = CompositeDisposable()

    @Inject
    lateinit var progress: Progress

    @Inject
    lateinit var logicRepository: ILogicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PDApplication).appComponent.inject(this)
    }

    protected fun request(disposable: Disposable) {
        disposableContainer.add(disposable)
    }

    protected fun <T> progress(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.doOnSubscribe({
                progress.show(supportFragmentManager, "progress")
            }).doOnSuccess({
                progress.dismiss()
            }).doOnError({
                progress.dismiss()
            }).doOnDispose {
                progress.dismiss()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        disposableContainer.clear()
    }
}