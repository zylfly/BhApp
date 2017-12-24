package com.tt.lvruheng.eyepetizer.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zyl.beheapp.mvp.model.bean.Result

interface GankContract {

    interface View : IBaseView {
        fun showGankList(bean: ArrayList<Result>)
    }

    interface Presenter : IPresenter<View> {
        fun requestData(page: Int)
    }

}