package com.zyl.beheapp.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zyl.beheapp.mvp.model.bean.Banner
import com.zyl.beheapp.mvp.model.bean.Item

interface SelectedContract {

    interface View : IBaseView {

        fun showSelectedList(HomeList: ArrayList<Item>?, bannerList: ArrayList<Banner>?)

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun getSelectedListData()

        fun getBannerListData()
    }
}