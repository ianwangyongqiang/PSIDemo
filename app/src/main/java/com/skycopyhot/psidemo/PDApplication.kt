package com.skycopyhot.psidemo

import android.app.Application
import com.skycopyhot.psidemo.di.AppComponent
import com.skycopyhot.psidemo.di.DaggerAppComponent

/**
 * Created by yongqiangwang on 17/2/18.
 * PDApplication
 */
class PDApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}