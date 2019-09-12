package com.wangyang.baselibrary.presenter

import com.wangyang.baselibrary.presenter.view.BaseView

abstract class BasePresenter<T : BaseView> {
     lateinit var mView: T
}