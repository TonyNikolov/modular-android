package com.android.modular_m_vm.dagger2.modules

import android.app.Application
import com.android.modular_m_vm.api.Api
import com.android.modular_m_vm.api.URL_API_BASE
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {
    companion object {
        private const val CACHE_SIZE = 10L * 1024 * 1024   //10MB
        private const val TIMEOUT = 30L    // Seconds
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.cache(Cache(application.cacheDir, CACHE_SIZE))
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS).connectTimeout(TIMEOUT, TimeUnit.SECONDS)


        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.networkInterceptors().add(httpLoggingInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): Api {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL_API_BASE)
            .client(okHttpClient)
            .build()
            .create(Api::class.java)
    }
}