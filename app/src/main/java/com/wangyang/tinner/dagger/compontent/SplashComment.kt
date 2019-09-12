package com.wangyang.tinner.dagger.compontent

import com.wangyang.tinner.dagger.moudle.SplashMoudle
import com.wangyang.tinner.ui.MainActivity
import com.wangyang.tinner.ui.SplashActivity
import dagger.Component

@Component(modules = [SplashMoudle::class])
interface SplashComment {

    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
}