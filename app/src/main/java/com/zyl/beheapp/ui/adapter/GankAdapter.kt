package com.zyl.beheapp.ui.adapter

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import com.zyl.beheapp.R
import com.zyl.beheapp.chAllDisplayImage
import com.zyl.beheapp.mvp.model.bean.Result
import com.zyl.beheapp.widget.recyclerview.ViewHolder
import com.zyl.beheapp.widget.recyclerview.adapter.CommonAdapter
import com.zyl.beheapp.widget.recyclerview.adapter.OnItemClickListener


class GankAdapter(mContext: Context, dataList: ArrayList<Result>, layoutId: Int)
    : CommonAdapter<Result>(mContext, dataList, layoutId) {

    fun addData(dataList: ArrayList<Result>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: Result, position: Int) {
        with(holder) {
            getView<ImageView>(R.id.iv_girl).chAllDisplayImage(mContext, data.url)
            //animator
            val ani = AnimationUtils.loadAnimation(mContext, R.anim.item_left_in)
            ani.interpolator = AnticipateOvershootInterpolator()
            itemView.startAnimation(ani)

            setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(obj: Any?, position: Int) {
                    onPosition?.OnCallBack(position)
                }
            })
        }
    }

    interface OnPosition {
        fun OnCallBack(pos: Int)
    }

    private var onPosition: OnPosition? = null

    fun getPostion(onPosition: OnPosition) {
        this.onPosition = onPosition
    }

}