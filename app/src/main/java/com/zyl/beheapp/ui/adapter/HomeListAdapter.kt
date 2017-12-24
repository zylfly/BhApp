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


class HomeListAdapter(mContext: Context, dataList: ArrayList<Item>, layoutId: Int)
    : CommonAdapter<Item>(mContext, dataList, layoutId) {

    fun addData(dataList: ArrayList<Item>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: Item, position: Int) {
        with(holder) {
            //getView<TextView>(R.id.homeListTime).text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(mData[position].updated_at * 1000))
            getView<ImageView>(R.id.homeListIv).chAllDisplayImage(mContext, mData[position].cover_image_url)
            getView<TextView>(R.id.homeListTitle).text = mData[position].title
            //animator
            val ani = AnimationUtils.loadAnimation(mContext, R.anim.item_left_in)
            ani.interpolator = AnticipateOvershootInterpolator()
            itemView.startAnimation(ani)

            itemView.setOnClickListener({
                var intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("url", mData[position].url)
                mContext.startActivity(intent)
            })
        }
    }

}