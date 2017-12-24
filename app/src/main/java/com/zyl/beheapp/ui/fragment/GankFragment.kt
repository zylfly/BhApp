package com.zyl.beheapp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.hazz.kotlinmvp.base.BaseFragment
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.tt.lvruheng.eyepetizer.mvp.contract.GankContract
import com.tt.lvruheng.eyepetizer.mvp.presenter.GankPresenter
import com.zyl.beheapp.R
import com.zyl.beheapp.mvp.model.bean.Result
import com.zyl.beheapp.ui.activity.ImageActivity
import com.zyl.beheapp.ui.adapter.GankAdapter
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_gank.*


class GankFragment : BaseFragment(), GankContract.View {

    private var mPage: Int = 1
    private var datas = ArrayList<Result>()
    private val mPresenter by lazy { GankPresenter() }

    private val mGankAdapter: GankAdapter? by lazy { GankAdapter(activity, datas, R.layout.item_girl) }

    override fun showLoading() {}

    override fun dismissLoading() {}

    override fun getLayoutId(): Int = R.layout.fragment_gank

    override fun lazyLoad() {
        mPresenter.requestData(mPage)
    }

    override fun showGankList(bean: ArrayList<Result>) {
        mGankAdapter!!.addData(bean)
    }

    override fun initView() {
        mPresenter.attachView(this)
        base_mTitle.text = "妹子"
        hRecycler.layoutManager = LinearLayoutManager(context)
        hRecycler.adapter = mGankAdapter
        mGankAdapter?.getPostion(object : GankAdapter.OnPosition {
            override fun OnCallBack(pos: Int) {
                val imageView = hRecycler.findViewHolderForAdapterPosition(pos)?.itemView?.findViewById<ImageView>(R.id.iv_girl) as ImageView
                ImageActivity.startActivity(activity, imageView, datas[pos].url)
            }
        })
        gankRefresh.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                datas.clear()
                mPage = 1
                mPresenter.requestData(mPage)
                refreshLayout!!.finishRefreshing()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                ++mPage
                mPresenter.requestData(mPage)
                refreshLayout!!.finishLoadmore()
            }
        })
    }

    companion object {
        fun newInstance(): GankFragment {
            val fragment = GankFragment()
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