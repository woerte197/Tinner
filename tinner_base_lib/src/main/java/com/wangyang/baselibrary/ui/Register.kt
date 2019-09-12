package com.wangyang.baselibrary.ui

import android.os.Bundle
import com.wangyang.baselibrary.googlemvp.RegisterContract
import com.wangyang.baselibrary.googlemvp.RegisterPresenter
import com.wangyang.baselibrary.ui.activity.BaseActivity

class Register(override var mPresenter: RegisterPresenter) : BaseActivity(), RegisterContract.View<RegisterPresenter> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = RegisterPresenter(this)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }
}