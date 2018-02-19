package com.skycopyhot.psidemo.di

import com.skycopyhot.psidemo.PDApplication
import com.skycopyhot.psidemo.ui.BaseActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by yongqiangwang on 17/2/18.
 * AppComponent
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: BaseActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PDApplication): Builder
        fun build(): AppComponent
    }
}