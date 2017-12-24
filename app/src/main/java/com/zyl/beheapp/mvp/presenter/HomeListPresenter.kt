package com.zyl.beheapp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.zyl.beheapp.mvp.contract.HomeCommContract
import com.zyl.beheapp.mvp.model.HomeListModel
import com.zyl.beheapp.mvp.model.bean.Item


class HomeListPresenter :BasePresenter<HomeCommContract.View> (),HomeCommContract.Presenter{

    val mModels: HomeListModel by lazy {
        HomeListModel()
    }
    override fun getHomeListData(tag:Int,generation:Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mModels.load(tag,generation).subscribe({t ->
            mRootView?.apply {
                dismissLoading()
                showHomeList(t.data.items as ArrayList<Item>)
            }
        },{t ->
            mRootView?.apply {
                dismissLoading()
                //处理异常
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })

        addSubscription(disposable)
    }
}