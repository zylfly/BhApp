package com.zyl.beheapp.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class NoViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    private var mScrollEnabled = true     //默认不滑动

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return mScrollEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return mScrollEnabled && super.onInterceptTouchEvent(event)
    }

    fun setScrollEnabled(enabled: Boolean) {
        mScrollEnabled = enabled
    }
}