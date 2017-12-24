package com.zyl.beheapp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.zyl.beheapp.mvp.contract.SingleContract
import com.zyl.beheapp.mvp.model.HomeListModel
import com.zyl.beheapp.mvp.model.bean.Item

class SinglePresenter : BasePresenter<SingleContract.View>(), SingleContract.Presenter {
    override fun getSingleListData() {

        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mModels.loadSingle().subscribe({t ->
            mRootView?.apply {
                dismissLoading()
                showSingleList(t.data.items as ArrayList<Item>)
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

    val mModels: HomeListModel by lazy {
        HomeListModel()
    }
}