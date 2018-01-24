package com.zyl.beheapp.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.zyl.beheapp.R
import com.zyl.beheapp.router.RouterApi
import com.zyl.beheapp.chAllDisplayImage
import com.zyl.beheapp.mvp.model.bean.Banner
import com.zyl.beheapp.mvp.model.bean.Item
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*


class SelectesdAdapter(val mContext: Context)
    : RecyclerView.Adapter<SelectesdAdapter.ViewHolder>() {

    companion object {
        private val ITEM_TYPE_BANNER = 1    //Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private val ITEM_TYPE_CONTENT = 3    //item
    }

    private var data = ArrayList<Item>()
    private var datas = ArrayList<Banner>()

    fun addData(dataList: ArrayList<Item>) {
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addBannerData(List: ArrayList<Banner>) {
        this.datas.addAll(List)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_BANNER
            1 -> ITEM_TYPE_TEXT_HEADER
            else -> ITEM_TYPE_CONTENT
        }
    }

    override fun getItemCount(): Int {
        return when {
            data.size > 0 -> data.size + 2
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return when (viewType) {
            ITEM_TYPE_BANNER -> ViewHolder(inflaterView(R.layout.item_home_banner, parent!!))
            ITEM_TYPE_TEXT_HEADER -> ViewHolder(inflaterView(R.layout.item_home_header, parent!!))
            else -> ViewHolder(inflaterView(R.layout.item_home_content, parent!!))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_BANNER -> {
                val bannerItemData: ArrayList<Banner> = datas.take(datas.size).toCollection(ArrayList())
                val bannerFeedList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()
                //取出banner 显示的 img 和 Title
                Observable.fromIterable(bannerItemData)
                        .subscribe({ list ->
                            bannerFeedList.add(list.image_url)
                            bannerTitleList.add(list.target.title)
                        })
                with(holder!!) {
                    banner.run {
                        setAutoPlayAble(datas.size > 1)
                        setData(bannerFeedList, bannerTitleList)
                        setAdapter(object : BGABanner.Adapter<ImageView, String> {
                            override fun fillBannerItem(bgaBanner: BGABanner?, imageView: ImageView?, feedImageUrl: String?, position: Int) {
                                Glide.with(mContext)
                                        .load(feedImageUrl)
                                        .transition(DrawableTransitionOptions().crossFade())
                                        .into(imageView)

                            }
                        })
                    }
                }

            }
            ITEM_TYPE_CONTENT ->
                with(holder!!) {
                    homeListTime.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date(data[position - 2].updated_at * 1000))
                    homeListIv.chAllDisplayImage(mContext, data[position - 2].cover_image_url)
                    homeListTitle.text = data[position - 2].title

                    //animator
                    val ani = AnimationUtils.loadAnimation(mContext, R.anim.item_left_in)
                    ani.interpolator = AnticipateOvershootInterpolator()
                    itemView.startAnimation(ani)

                    itemView.setOnClickListener({
                        ARouter.getInstance().build(RouterApi.DETAIL)
                                .withString("url",data[position].url)
                                .navigation()
                    })
                }
            ITEM_TYPE_TEXT_HEADER ->
                holder?.homeHeadContext?.text = "---------数据---------"
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var homeListIv = itemView.findViewById<ImageView>(R.id.homeListIv)
        var homeListTitle = itemView.findViewById<TextView>(R.id.homeListTitle)
        var homeListTime = itemView.findViewById<TextView>(R.id.homeListTime)
        var homeHeadContext = itemView.findViewById<TextView>(R.id.homeHeadContext)
        var banner = itemView.findViewById<BGABanner>(R.id.banner)

    }

    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = LayoutInflater.from(parent.context)?.inflate(mLayoutId, parent, false)
        return view!!
    }


}