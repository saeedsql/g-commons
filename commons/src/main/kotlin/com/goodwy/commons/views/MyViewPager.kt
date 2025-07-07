package com.devgroup.commons.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class MyViewPager : ViewPager {
    private var isPagingEnabled = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return try {
            isPagingEnabled && super.onInterceptTouchEvent(ev)
        } catch (ignored: Exception) {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return try {
            if (isPagingEnabled) {
                performClick()
                super.onTouchEvent(ev)
            } else {
                false
            }
        } catch (ignored: Exception) {
            false
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    fun setPagingEnabled(enable: Boolean) {
        isPagingEnabled = enable
    }
}
