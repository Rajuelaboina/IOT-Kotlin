package com.thingspeak.upw_iot.apis

import android.content.Context
import com.thingspeak.upw_iot.utils.SharedPrefManager.Companion.context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitRequest {
   // companion object{

        fun getRetrofitInstance(): ApiRequest {
            val BASE_URL="https://api.thingspeak.com"
           // val BASE_URL="https://www.universal-tutorial.com/"
            /*val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build()*/
            val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB cache size, because we like to keep things moderate
            val cache = Cache(context.cacheDir,cacheSize)
            val cachingInterceptor = Interceptor {
                    chain ->
                val originalResponse = chain.proceed(chain.request())
                val cacheControl = originalResponse.header("Cache-Control")
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
                ) {
                    // No cache headers, skip caching
                    originalResponse
                } else {
                    val maxAge = 60 // Cache for 1 minute, because patience is not always a virtue
                    originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                }
            }
            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(cachingInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiRequest::class.java)
        }
  //  }
}