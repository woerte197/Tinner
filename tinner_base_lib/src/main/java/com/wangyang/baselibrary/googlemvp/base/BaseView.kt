package com.wangyang.baselibrary.googlemvp.base

interface BaseView<T : BasePresenter> {
    var mPresenter: T
    fun showLoading()
    fun hideLoading()
}