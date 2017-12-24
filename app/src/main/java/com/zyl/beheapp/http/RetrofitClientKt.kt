package com.zyl.beheapp.http

import com.zyl.beheapp.App
import com.zyl.beheapp.http.GankService.Companion.BASE_URL
import com.zyl.beheapp.utils.CaheIntercept
import com.zyl.beheapp.utils.NetworkUtils
import com.zyl.zlogger.BuildConfig
import com.zyl.zlogger.Level
import com.zyl.zlogger.LoggingInterceptor
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.*
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Suppress("SENSELESS_COMPARISON")
class RetrofitClientKt private constructor() {

    private var mRetrofit: Retrofit

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    //.header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    /**
     * 设置缓存
     */
    private fun addCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.isNetworkAvailable(App.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (NetworkUtils.isNetworkAvailable(App.context)) {
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }
            response
        }
    }


    //主构造初始化块
    init {
        val cacheSize = 1024 * 1024 * 10L
        val cacheDir = File(App.context.cacheDir, "zyl")
        val cache = Cache(cacheDir, cacheSize)
        val okhttpClient = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder()) //RetrofitUrlManager 初始化
                .connectTimeout(6000, TimeUnit.MILLISECONDS)
                .readTimeout(6000, TimeUnit.MILLISECONDS)
                .writeTimeout(6000, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addInterceptor(CaheIntercept(App.context))
                //.addInterceptor(addHeaderInterceptor())
                .addInterceptor(LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(true) //是否开启日志打印
                        .setLevel(Level.BODY) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("zylLog") // request的Tag
                        .response("zylLog")// Response的Tag
                        .addHeader("version", BuildConfig.VERSION_NAME)//打印版本
                        .build()
                )
                .build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build()
    }

    companion object {
        @Volatile
        private var instance: RetrofitClientKt? = null

        fun instances(): RetrofitClientKt {
            if (instance == null) {
                synchronized(RetrofitClientKt::class) {
                    if (instance == null) {
                        instance = RetrofitClientKt()
                    }
                }
            }
            return instance!!
        }
    }

    val service: GankService by lazy { mRetrofit.create(GankService::class.java) }

}