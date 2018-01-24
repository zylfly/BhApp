package com.zyl.beheapp.Router

import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by Administrator on 2018/1/19 0019.
 */
object RouterApi {

    const val HOME = "/bhApp/home"//主页
    const val ACTIVITY = "/bhApp/activity"//第二个页面
    const val KOTLIN = "/bhApp/kotlin"//第san个页面
    const val CHAT = "/bhApp/activity"//第


    const val HOMEPAGEFRAGMENT = "/bhApp/homePageFragment"//首页
    const val SINGLEFRAGMENT = "/bhApp/singleFragment"//单品
    const val GANKFRAGMENT = "/bhApp/gankFragment"//干货
    const val MINEFRAGMENT = "/bhApp/mineFragment"//我的


    fun router(path:String){
        ARouter.getInstance().build(path).navigation()
    }
}