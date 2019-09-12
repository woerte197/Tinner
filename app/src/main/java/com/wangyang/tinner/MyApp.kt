package com.wangyang.tinner

import com.alibaba.android.arouter.launcher.ARouter
import com.wangyang.baselibrary.BaseApplication
import com.wangyang.baselibrary.config.ConfigManager

class MyApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        super.onCreate()
        ConfigManager.init(this)
            .addWechatAppId("sadsadsadsad")
            .addHostUrl("http://www.baidu.com")
            .build()
        isDebug = true
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }

}