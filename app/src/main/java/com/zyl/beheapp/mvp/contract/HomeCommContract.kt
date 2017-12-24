package com.zyl.beheapp.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zyl.beheapp.mvp.model.bean.Item

interface HomeCommContract {

    interface View : IBaseView {

        fun showHomeList(HomeList: ArrayList<Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{

        fun getHomeListData(tag:Int,generation:Int)
    }
}