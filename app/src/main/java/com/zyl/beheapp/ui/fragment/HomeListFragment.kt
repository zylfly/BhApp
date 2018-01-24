package com.zyl.beheapp.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.hazz.kotlinmvp.base.BaseFragment
import com.zyl.beheapp.R
import com.zyl.beheapp.mvp.contract.HomeCommContract
import com.zyl.beheapp.mvp.model.bean.Item
import com.zyl.beheapp.mvp.presenter.HomeListPresenter
import com.zyl.beheapp.router.RouterApi
import com.zyl.beheapp.ui.adapter.HomeListAdapter
import kotlinx.android.synthetic.main.fragment_homelist.*

@Route(path = RouterApi.HOMELISTFRAGMENT)
class HomeListFragment : BaseFragment(), HomeCommContract.View {

    @Autowired
    @JvmField var tag: Int? = null
    @Autowired
    @JvmField var generation: Int? = null

    private var datas = ArrayList<Item>()
    private val mPresenter by lazy { HomeListPresenter() }
    private val mAdapter: HomeListAdapter by lazy { HomeListAdapter(activity, datas, R.layout.item_home_list) }

    override fun showLoading() {}

    override fun dismissLoading() {}

    override fun showHomeList(HomeList: ArrayList<Item>) {
        mAdapter.addData(HomeList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {}

    override fun initView() {
        mPresenter.attachView(this)
        homeListRv.layoutManager = LinearLayoutManager(activity)
        homeListRv.adapter = mAdapter
        homeListRefresh.setPureScrollModeOn()
    }

    override fun lazyLoad() {
        mPresenter.getHomeListData(this.tag!!, this.generation!!)
    }

    override fun getLayoutId(): Int = R.layout.fragment_homelist


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}