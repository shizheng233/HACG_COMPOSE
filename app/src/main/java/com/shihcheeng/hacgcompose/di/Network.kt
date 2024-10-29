package com.shihcheeng.hacgcompose.di

import com.shihcheeng.hacgcompose.components.setting.AppSetting
import com.shihcheeng.hacgcompose.networkservice.HacgService
import com.shihcheeng.hacgcompose.utils.JsoupTransferFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {

    private val hacgHeaders = mapOf(
        "Referer" to "https://www.hacg.mov/",
        "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36",
        "Priority" to "u=0, i"
    ).toHeaders()

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .removeHeader("User-Agent")
                    .headers(hacgHeaders)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideHacgService(
        client: OkHttpClient,
        appSetting: AppSetting
    ): HacgService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(appSetting.host.get())
            .addConverterFactory(JsoupTransferFactory())
            .build()
            .create(HacgService::class.java)
    }

}