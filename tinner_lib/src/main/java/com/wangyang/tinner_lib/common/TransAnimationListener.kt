package com.wangyang.tinner_lib.common

import android.view.animation.Animation

interface TransAnimationListener :Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
    }

    override fun onAnimationStart(animation: Animation?) {
    }
}