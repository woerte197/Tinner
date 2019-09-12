package com

import android.app.Application
import com.mob.MobSDK
import com.wangyang.baselibrary.IApplication

class ShareApplication :IApplication{
    override fun onCreate(application: Application) {
        MobSDK.init(application)
    }

}