package com.wangyang.baselibrary.ui.fragment

import android.os.Bundle
import com.wangyang.baselibrary.presenter.BasePresenter
import com.wangyang.baselibrary.presenter.view.BaseView

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    protected lateinit var mPresenter: T


    override fun showLoading() {
    }

    override fun hideLoading() {
    }


}