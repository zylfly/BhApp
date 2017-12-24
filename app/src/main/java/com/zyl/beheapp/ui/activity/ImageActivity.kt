package com.zyl.beheapp.ui.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hazz.kotlinmvp.base.BaseActivity
import com.zyl.beheapp.R
import kotlinx.android.synthetic.main.activity_image.*


class ImageActivity : BaseActivity() {

    private var girlUrl: String? = null
    override fun layoutId(): Int = R.layout.activity_image

    override fun initData() {}

    override fun initView() {
        girlUrl = intent.getStringExtra(IMG)
        Glide.with(this).load(girlUrl).into(girlPhoto)
        girlPhoto.setOnClickListener({
            supportFinishAfterTransition()
        })
    }

    override fun start() {
    }

    companion object {
        val IMG = "IMG"
        fun startActivity(context: Context, imageView: ImageView, url: String) {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(IMG, url)
            if (Build.VERSION.SDK_INT > 21) {
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context as Activity, imageView, "img").toBundle())
            } else {
                context.startActivity(intent)
            }
        }

    }
}