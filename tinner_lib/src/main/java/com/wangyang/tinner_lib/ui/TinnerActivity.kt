package com.wangyang.tinner_lib.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.wangyang.baselibrary.presenter.BasePresenter
import com.wangyang.baselibrary.ui.activity.BaseMvpActivity
import com.wangyang.tinner_lib.R
import com.wangyang.tinner_lib.StartsBarUtil

abstract class TinnerActivity<T : BasePresenter<*>> : BaseMvpActivity<T>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        StartsBarUtil.setWindowsBarTranslation(this,  ContextCompat.getColor(this, R.color.app_main))
    }
}