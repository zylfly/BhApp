package com.zyl.beheapp.router

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by Administrator on 2018/1/19 0019.
 */
object RouterApi {

    const val HOME = "/bhApp/home"//主页
    const val DETAIL = "/bhApp/detail"//详情

    const val SELECTEDSFRAGMENT = "/bhApp/selectedsFragment"//第一页
    const val HOMELISTFRAGMENT = "/bhApp/homeListFragment"//后面几页

    const val HOMEPAGEFRAGMENT = "/bhApp/homePageFragment"//首页
    const val SINGLEFRAGMENT = "/bhApp/singleFragment"//单品
    const val GANKFRAGMENT = "/bhApp/gankFragment"//干货
    const val MINEFRAGMENT = "/bhApp/mineFragment"//我的


    fun router(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    fun routerFragment(path: String): Fragment {
        return ARouter.getInstance().build(path).navigation() as Fragment
    }
}