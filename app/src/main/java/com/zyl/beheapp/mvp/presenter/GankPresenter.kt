package com.tt.lvruheng.eyepetizer.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.tt.lvruheng.eyepetizer.mvp.contract.GankContract
import com.tt.lvruheng.eyepetizer.mvp.model.GankModel
import com.zyl.beheapp.mvp.model.bean.Result


class GankPresenter : BasePresenter<GankContract.View>(), GankContract.Presenter {


    val mModel: GankModel by lazy {
        GankModel()
    }

    override fun requestData(page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mModel.loadGank(page).subscribe({ homeBean ->
            mRootView?.apply {
                dismissLoading()
                showGankList(homeBean.results as ArrayList<Result>)
            }
        }, {
            mRootView?.apply {
                dismissLoading()
                //处理异常
            }

        })

        addSubscription(disposable)

    }


}





