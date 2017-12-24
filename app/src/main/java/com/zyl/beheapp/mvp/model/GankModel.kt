package com.tt.lvruheng.eyepetizer.mvp.model


import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.zyl.beheapp.http.RetrofitClientKt
import com.zyl.beheapp.mvp.model.bean.GankBean
import io.reactivex.Observable

class GankModel {
    fun loadGank(page: Int): Observable<GankBean> {
        return RetrofitClientKt.instances().service
                .getGankLists(page).compose(SchedulerUtils.ioToMain())
    }
}