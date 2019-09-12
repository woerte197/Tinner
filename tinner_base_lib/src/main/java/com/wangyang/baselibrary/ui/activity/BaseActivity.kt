package com.wangyang.baselibrary.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.wangyang.baselibrary.BaseApplication
import com.wangyang.baselibrary.injection.component.AppComponent

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        val appComponent: AppComponent? = BaseApplication.appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

}