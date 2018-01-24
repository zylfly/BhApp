package com.zyl.beheapp.ui.activity

import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.hazz.kotlinmvp.base.BaseActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.ChromeClientCallbackManager
import com.zyl.beheapp.R
import com.zyl.beheapp.router.RouterApi
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.base_title.*

@Route(path = RouterApi.DETAIL)
class DetailActivity : BaseActivity() {

    @Autowired
    @JvmField var url: String? = null

    private var aWebView: AgentWeb? = null

    override fun layoutId(): Int = R.layout.activity_detail

    override fun initView() {
        aWebView = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(llDetail, LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(url)
    }

    private val mCallback = ChromeClientCallbackManager.ReceivedTitleCallback { _, title ->
        if (base_mTitle != null)
            base_mTitle.text = title
    }

    override fun initData() {}

    override fun start() {}

    override fun onPause() {
        aWebView!!.webLifeCycle!!.onPause()
        super.onPause()
    }

    override fun onResume() {
        aWebView!!.webLifeCycle!!.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        aWebView!!.webLifeCycle!!.onDestroy()
    }
}