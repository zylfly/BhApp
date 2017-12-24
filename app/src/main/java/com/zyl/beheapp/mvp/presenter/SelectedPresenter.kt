package com.zyl.beheapp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.zyl.beheapp.mvp.contract.SelectedContract
import com.zyl.beheapp.mvp.model.HomeListModel
import com.zyl.beheapp.mvp.model.bean.Banner
import com.zyl.beheapp.mvp.model.bean.Item


class SelectedPresenter : BasePresenter<SelectedContract.View>(), SelectedContract.Presenter {

    var datas = ArrayList<Item>()
    var data = ArrayList<Banner>()

    val mModel: HomeListModel by lazy {
        HomeListModel()
    }

    override fun getSelectedListData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mModel.loadSelected().subscribe({ t ->
            mRootView?.apply {
                dismissLoading()
                datas = t.data.items as ArrayList<Item>
                showSelectedList(datas, data)
            }
        }, { t ->
            mRootView?.apply {
                dismissLoading()
                //处理异常
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })

        addSubscription(disposable)
    }

    override fun getBannerListData() {
        val disposable = mModel.loadBanner().subscribe({ t ->
            mRootView?.apply {
                data = t.data.banners as ArrayList<Banner>
                showSelectedList(datas, data)
            }
        }, { t ->
            mRootView?.apply {
                //处理异常
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })

        if (disposable != null)
            addSubscription(disposable)
    }
}