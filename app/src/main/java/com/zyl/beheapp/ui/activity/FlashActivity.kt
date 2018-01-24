package com.zyl.beheapp.ui.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.zyl.beheapp.R
import com.zyl.beheapp.router.RouterApi


class FlashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)

        Handler().postDelayed({
            RouterApi.router(RouterApi.HOME)
            finish()
        }, 1500)
    }
}