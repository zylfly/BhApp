package com.zyl.beheapp.ui.fragment

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.base.BaseFragmentAdapter
import com.zyl.beheapp.R
import com.zyl.beheapp.router.RouterApi
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*

@Route(path = RouterApi.HOMEPAGEFRAGMENT)
class HomePageFragment : BaseFragment() {

    private val mFragments = ArrayList<Fragment>()
    private val mTitles = arrayOf("精选", "卧室", "书房", "卫浴", "厨房", "客厅")

    override fun initView() {
        base_mTitle.text = "薄荷家居"
        mFragments.add(RouterApi.routerFragment(RouterApi.SELECTEDSFRAGMENT))
        mFragments.add(ARouter.getInstance().build(RouterApi.HOMELISTFRAGMENT).withInt("tag",13).withInt("generation",4).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterApi.HOMELISTFRAGMENT).withInt("tag",16).withInt("generation",4).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterApi.HOMELISTFRAGMENT).withInt("tag",14).withInt("generation",4).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterApi.HOMELISTFRAGMENT).withInt("tag",12).withInt("generation",1).navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build(RouterApi.HOMELISTFRAGMENT).withInt("tag",15).withInt("generation",4).navigation() as Fragment)

        homePageVp.adapter = BaseFragmentAdapter(childFragmentManager, mFragments, mTitles)
        homePageTab.setViewPager(homePageVp)
    }

    override fun lazyLoad() {}

    override fun getLayoutId(): Int = R.layout.fragment_homepage
}