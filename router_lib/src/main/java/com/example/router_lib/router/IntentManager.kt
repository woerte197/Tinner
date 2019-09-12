package com.example.router_lib.router

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

class IntentManager {
    private lateinit var postcard: Postcard

    companion object {
        val map: MutableMap<String, Any> = HashMap()
        val INS: IntentManager by lazy { IntentManager() }
    }

    fun init(path: String): IntentManager {
        postcard = ARouter.getInstance().build(path)
        return this
    }

    fun withBundle(bundle: Bundle): IntentManager {
        postcard.with(bundle)
        return this
    }

    fun withObj(key: String, obj: Any): IntentManager {
        map[key] = obj
        postcard.withObject(key, obj)
        return this
    }

    fun greenChannel():IntentManager{
        postcard.greenChannel()
        return this
    }


    @JvmOverloads
    fun navigation(context: Context? = null, callback: NavigationCallback? = null): Any? {
        return postcard.navigation(context, callback)
    }

    @JvmOverloads
    fun navigation(activity: Activity, requestCode: Int, callback: NavigationCallback? = null) {
        postcard.navigation(activity, requestCode, callback)
    }


}