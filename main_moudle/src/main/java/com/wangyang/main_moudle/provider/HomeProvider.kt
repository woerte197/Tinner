package com.wangyang.main_moudle.provider

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.router_lib.provider.base.BaseProvider
import com.example.router_lib.provider.moudle.home.IHomeProvider

@Route(path = IHomeProvider.HOME_SERVICE)
class HomeProvider:BaseProvider(),IHomeProvider{
    override fun init() {

    }
}