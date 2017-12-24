package com.zyl.beheapp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.hazz.kotlinmvp.base.BaseFragment
import com.zyl.beheapp.R
import com.zyl.beheapp.mvp.contract.SingleContract
import com.zyl.beheapp.mvp.model.bean.Item
import com.zyl.beheapp.mvp.presenter.SinglePresenter
import com.zyl.beheapp.ui.adapter.SingleListAdapter
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_single.*

class SingleFragment : BaseFragment(), SingleContract.View {

    private var mData = ArrayList<Item>()
    private val mPresenter by lazy { SinglePresenter() }
    private val mAdapter: SingleListAdapter by lazy {
        SingleListAdapter(activity, mData, R.layout.item_single)
    }

    override fun showLoading() {}

    override fun dismissLoading() {}


    override fun showSingleList(HomeList: ArrayList<Item>) {
        mAdapter.addData(HomeList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {}

    override fun initView() {
        base_mTitle.text = "单品"
        mPresenter.attachView(this)
        singleListRv.layoutManager = GridLayoutManager(activity, 2)
        singleListRv.adapter = mAdapter
        singleRefresh.setPureScrollModeOn()
    }

    override fun lazyLoad() {
        mPresenter.getSingleListData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_single

    companion object {
        fun newInstance(): SingleFragment {
            val fragment = SingleFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}