package com.zyl.beheapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.zyl.beheapp.R
import com.zyl.beheapp.chAllDisplayImage
import com.zyl.beheapp.mvp.model.bean.Item
import com.zyl.beheapp.ui.activity.DetailActivity
import com.zyl.beheapp.widget.recyclerview.ViewHolder
import com.zyl.beheapp.widget.recyclerview.adapter.CommonAdapter
import java.util.*

class SingleListAdapter(mContext: Context, dataList: ArrayList<Item>, layoutId: Int)
    : CommonAdapter<Item>(mContext, dataList, layoutId) {


    var intent: Intent? = null
    fun addData(dataList: ArrayList<Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }


    override fun bindData(holder: ViewHolder, data: Item, position: Int) {
        with(holder) {
            getView<TextView>(R.id.singlePrice).text = "￥" + mData[position].data.price
            getView<ImageView>(R.id.homeListIv).chAllDisplayImage(mContext, mData[position].data.cover_image_url)
            getView<TextView>(R.id.singleTitle).text = mData[position].data.name

            //animator
            val ani = AnimationUtils.loadAnimation(mContext, R.anim.item_bottom_in)
            ani.interpolator = AnticipateOvershootInterpolator()
            itemView.startAnimation(ani)

            itemView.setOnClickListener({
                intent = Intent(mContext, DetailActivity::class.java)
                intent!!.putExtra("url", mData[position].data.url)
                mContext.startActivity(intent)
            })
        }
    }


}