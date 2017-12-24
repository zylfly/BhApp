package com.zyl.beheapp.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zyl.beheapp.mvp.model.bean.Item

interface SingleContract {

    interface View : IBaseView {

        fun showSingleList(HomeList: ArrayList<Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {

        fun getSingleListData()
    }
}