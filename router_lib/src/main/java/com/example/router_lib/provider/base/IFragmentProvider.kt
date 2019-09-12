package com.example.router_lib.provider.base


import android.support.v4.app.Fragment


interface IFragmentProvider : IBaseProvider {

    fun newInstance(type: String): Fragment

}
