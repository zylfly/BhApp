package com.zyl.beheapp.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.widget.Toast
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.base.BaseActivity
import com.hazz.kotlinmvp.base.BaseFragmentAdapter
import com.zyl.beheapp.R
import com.zyl.beheapp.mvp.model.bean.TabEntity
import com.zyl.beheapp.ui.fragment.GankFragment
import com.zyl.beheapp.ui.fragment.HomePageFragment
import com.zyl.beheapp.ui.fragment.MineFragment
import com.zyl.beheapp.ui.fragment.SingleFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : BaseActivity() {

    private val mTitles = arrayOf("首页", "单品", "福利", "个人")
    private val mIconUnselectIds = intArrayOf(R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect)
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.tab_contact_select, R.mipmap.tab_more_select)
    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var mFragments: MutableList<Fragment> = arrayListOf()

    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
        initTab()
    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnselectIds[it]) }
        //为Tab赋值
        homeBottomTab.setTabData(mTabEntities)
        homeBottomTab.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                homeBottomVp.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }
        })

//        homeBottomVp.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
//            override fun getItem(position: Int): Fragment = mFragments[position]
//            override fun getCount(): Int = mFragments.size
//            override fun getPageTitle(position: Int): CharSequence = mTitles[position]
//        }

        homeBottomVp.adapter = BaseFragmentAdapter(supportFragmentManager, mFragments, mTitles)

        homeBottomVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                homeBottomTab.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        //缓存页面数量
        homeBottomVp.offscreenPageLimit = 4
        homeBottomVp.currentItem = 0
    }

    private fun initFragments() {
        mFragments.add(HomePageFragment.newInstance())
        mFragments.add(SingleFragment.newInstance())
        mFragments.add(GankFragment.newInstance())
        mFragments.add(MineFragment.newInstance())
    }

    override fun initData() {}

    override fun initView() {}

    override fun start() {}

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}