package com.zyl.beheapp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.zyl.beheapp.http.GankService.Companion.API_DOMAIN_NAME
import com.zyl.beheapp.http.GankService.Companion.BASE_URL
import com.zyl.beheapp.http.GankService.Companion.BASE_URLS
import com.zyl.beheapp.http.GankService.Companion.GANK_DOMAIN_NAME
import com.zyl.zlogger.ZLogger
import me.jessyan.retrofiturlmanager.RetrofitUrlManager


//ImageView扩展函数
fun ImageView.chAllDisplayImage(activity: Context, strUrl: String?) {
    Glide.with(activity).load(strUrl).transition(DrawableTransitionOptions().crossFade(500)).apply {
        centerCropTransform().skipMemoryCache(true).priority(Priority.HIGH)
    }.into(this).onDestroy()
}


class App : Application() {

    private var refWatcher: RefWatcher? = null

    init {
        context = this
    }

    override fun onCreate() {
        super.onCreate()
        //初始化log日志
        ZLogger.init(isDebug)
        if (isDebug) {
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
        refWatcher = setupLeakCanary()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)

        //将每个 BaseUrl 进行初始化,运行时可以随时改变 DOMAIN_NAME 对应的值,从而达到改变 BaseUrl 的效果
        RetrofitUrlManager.getInstance().putDomain(GANK_DOMAIN_NAME, BASE_URL)
        RetrofitUrlManager.getInstance().putDomain(API_DOMAIN_NAME, BASE_URLS)

    }

    companion object {
        lateinit var context: App
        //开启日志（true为开启，false为关闭）
        var isDebug: Boolean = true

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApp = context.applicationContext as App
            return myApp.refWatcher
        }

    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    }

}