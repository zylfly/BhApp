package com.zyl.beheapp.ui.fragment

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.hazz.kotlinmvp.base.BaseFragment
import com.zyl.beheapp.R
import com.zyl.beheapp.router.RouterApi
import com.zyl.beheapp.widget.recyclerview.ViewHolder
import com.zyl.beheapp.widget.recyclerview.adapter.CommonAdapter
import kotlinx.android.synthetic.main.fragment_mine.*
import java.util.*


@Route(path = RouterApi.MINEFRAGMENT)
class MineFragment : BaseFragment() {

    var list = ArrayList<String>()

    private val mAdapter: MAdapter by lazy {
        MAdapter(activity, list, R.layout.item_layout)
    }

    override fun initView() {
        var i = 0
        while (i < 2) {
            i++
            list.add("结束结束")
        }
        collapsing_toolbar_layout.title = "踩踩"
        collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE) //设置还没收缩时状态下字体颜色
        collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK) //设置收缩后Toolbar上字体的颜色
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter

        mAdapter.addData(list)
    }

    override fun lazyLoad() {}

    override fun getLayoutId(): Int = R.layout.fragment_mine


    class MAdapter(mContext: Context, dataList: ArrayList<String>, layoutId: Int)
        : CommonAdapter<String>(mContext, dataList, layoutId) {


        fun addData(dataList: ArrayList<String>) {
            this.mData.addAll(dataList)
            notifyDataSetChanged()
        }

        override fun bindData(holder: ViewHolder, data: String, position: Int) {
            with(holder) {
                if (position == 0)
                    getView<TextView>(R.id.tvMine).text = mData[position]
            }
        }
    }

}

