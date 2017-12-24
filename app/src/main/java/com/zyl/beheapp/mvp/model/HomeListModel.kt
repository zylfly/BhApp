package com.zyl.beheapp.mvp.model

import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.zyl.beheapp.http.RetrofitClientKt
import com.zyl.beheapp.mvp.model.bean.HomeListsBean
import io.reactivex.Observable

class HomeListModel {
    fun load(tag: Int, generation: Int): Observable<HomeListsBean> {
        return RetrofitClientKt.instances().service
                .getBedroomList(tag, generation).compose(SchedulerUtils.ioToMain())
    }

    fun loadSingle(): Observable<HomeListsBean> {
        return RetrofitClientKt.instances().service
                .getSingleList().compose(SchedulerUtils.ioToMain())
    }

    //--------------------------精选-------------------------------
    fun loadSelected(): Observable<HomeListsBean> {
        return RetrofitClientKt.instances()
                .service.getHomeLists().compose(SchedulerUtils.ioToMain())
    }

    fun loadBanner(): Observable<HomeListsBean> {
        return RetrofitClientKt.instances()
                .service.getHomeBanner().compose(SchedulerUtils.ioToMain())
    }
}