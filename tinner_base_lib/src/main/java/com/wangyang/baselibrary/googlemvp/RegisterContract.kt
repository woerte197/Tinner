package com.wangyang.baselibrary.googlemvp

import com.wangyang.baselibrary.googlemvp.base.BasePresenter
import com.wangyang.baselibrary.googlemvp.base.BaseView

interface RegisterContract {

    interface View<T : Presenter> : BaseView<T>

    interface Presenter : BasePresenter

}