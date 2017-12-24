package com.zyl.beheapp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hazz.kotlinmvp.base.BaseFragment
import com.zyl.beheapp.R
import com.zyl.beheapp.mvp.contract.SelectedContract
import com.zyl.beheapp.mvp.model.bean.Banner
import com.zyl.beheapp.mvp.model.bean.Item
import com.zyl.beheapp.mvp.presenter.SelectedPresenter
import com.zyl.beheapp.ui.adapter.SelectesdAdapter
import kotlinx.android.synthetic.main.fragment_selected.*
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import me.jessyan.retrofiturlmanager.onUrlChangeListener
import okhttp3.HttpUrl


class SelectedsFragment : BaseFragment(), SelectedContract.View {

    private val mPresenter by lazy { SelectedPresenter() }

    override fun showLoading() {}

    override fun dismissLoading() {}


    override fun showSelectedList(HomeList: ArrayList<Item>?, bannerList: ArrayList<Banner>?) {
        mAdapter.addData(HomeList!!)
        mAdapter.addBannerData(bannerList!!)
    }

    override fun showError(errorMsg: String, errorCode: Int) {}

    private var mListener: ChangeListener? = null
    private val mAdapter: SelectesdAdapter by lazy {
        SelectesdAdapter(activity)
    }

    override fun initView() {
        mPresenter.attachView(this)
        selectedRv.layoutManager = LinearLayoutManager(activity)
        selectedRv.adapter = mAdapter
        this.mListener = ChangeListener()
        RetrofitUrlManager.getInstance().registerUrlChangeListener(mListener)
    }

    override fun lazyLoad() {
        mPresenter.getSelectedListData()
        mPresenter.getBannerListData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_selected

    companion object {
        fun newInstance(): SelectedsFragment {
            val fragment = SelectedsFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RetrofitUrlManager.getInstance().unregisterUrlChangeListener(mListener) //记住注销监听器
        mPresenter.detachView()
    }

    //baseUrl改变时候的监听
    private inner class ChangeListener : onUrlChangeListener {

        override fun onUrlChangeBefore(oldUrl: HttpUrl, domainName: String) {
        }

        override fun onUrlChanged(newUrl: HttpUrl, oldUrl: HttpUrl) {}
    }
}