package com.example.router_lib.provider.base

import android.content.Context
import android.support.v4.app.Fragment
import com.example.router_lib.router.IntentManager

abstract class BaseProvider : IFragmentProvider {
    lateinit var mContext: Context
    override fun init(context: Context) {
        mContext = context
        init()
    }

    abstract fun init()

    override fun newInstance(type: String): Fragment {
        try {
            return IntentManager.INS.init(type).navigation() as Fragment
        } catch (e: Exception) {
            throw RuntimeException("This Type is not fragment!")
        }
    }
}