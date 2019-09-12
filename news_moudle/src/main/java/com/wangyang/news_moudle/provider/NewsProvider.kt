package com.wangyang.news_moudle.provider

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.router_lib.provider.base.BaseProvider
import com.example.router_lib.provider.moudle.news.INewsProvider

@Route(path = INewsProvider.NEWS_SERVICE)
class NewsProvider :BaseProvider(), INewsProvider{
    override fun init() {

    }

}