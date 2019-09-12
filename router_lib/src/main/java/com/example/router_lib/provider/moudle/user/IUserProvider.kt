package com.example.router_lib.provider.moudle.user

import com.example.router_lib.provider.base.IFragmentProvider

interface IUserProvider : IFragmentProvider {
    companion object {
        const val USER_SERVICE = "/user0/user/service"
        const val USER_LOGIN = "/user0/user/login"
    }

    fun isLogin()
}