package com.example.router_lib.provider.moudle.home

import com.example.router_lib.provider.base.IFragmentProvider


interface IHomeProvider : IFragmentProvider {
    companion object {
        const val HOME_SERVICE = "/home/home/service"
        const val HOME_FRAGMENT = "/home/home/homeFragment"
    }


}
