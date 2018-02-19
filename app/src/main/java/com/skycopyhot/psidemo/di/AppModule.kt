package com.skycopyhot.psidemo.di

import android.content.Context
import com.google.gson.Gson
import com.skycopyhot.psidemo.PDApplication
import com.skycopyhot.psidemo.R
import com.skycopyhot.psidemo.logic.ILogicRepository
import com.skycopyhot.psidemo.logic.LogicRepository
import com.skycopyhot.psidemo.module.APIService
import com.skycopyhot.psidemo.ui.Progress
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by yongqiangwang on 17/2/18.
 * AppModule
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: PDApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttp(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val builder = it.request().newBuilder()
                    builder.addHeader("api-key",context.getString(R.string.gov_api_key))
                    it.proceed(builder.build())
                }
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(okHttpClient: OkHttpClient, context: Context): APIService {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.gov_api_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideLogicRepository(apiService: APIService, context: Context): ILogicRepository = LogicRepository(apiService, context)

    @Provides
    @Singleton
    fun provideDialog(): Progress = Progress()
}