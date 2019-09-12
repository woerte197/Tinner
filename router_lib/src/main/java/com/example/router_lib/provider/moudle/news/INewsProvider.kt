package com.example.router_lib.provider.moudle.news

import com.example.router_lib.provider.base.IFragmentProvider

interface INewsProvider:IFragmentProvider {
    companion object{
        const val NEWS_SERVICE = "/news/news/service"
        const val NEWS_FRAGMENT = "/news/news/fragment"
    }
}