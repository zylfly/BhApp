package com.zyl.beheapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.base.BaseFragmentAdapter
import com.zyl.beheapp.R
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.util.*

class HomePageFragment : BaseFragment() {

    private val mFragments = ArrayList<Fragment>()
    private val mTitles = arrayOf("精选", "卧室", "书房", "卫浴", "厨房", "客厅")

    override fun initView() {
        base_mTitle.text = "薄荷家居"
        mFragments.add(SelectedsFragment.newInstance())
        mFragments.add(HomeListFragment.newInstance(13, 4))
        mFragments.add(HomeListFragment.newInstance(16, 4))
        mFragments.add(HomeListFragment.newInstance(14, 4))
        mFragments.add(HomeListFragment.newInstance(12, 1))
        mFragments.add(HomeListFragment.newInstance(15, 4))

        homePageVp.adapter = BaseFragmentAdapter(childFragmentManager, mFragments, mTitles)
        homePageTab.setViewPager(homePageVp)
    }

    override fun lazyLoad() {}

    override fun getLayoutId(): Int = R.layout.fragment_homepage

    companion object {
        fun newInstance(): HomePageFragment {
            val fragment = HomePageFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}