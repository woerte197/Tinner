package com.wangyang.baselibrary.config

import android.content.Context
import okhttp3.Interceptor
import java.util.*
import kotlin.collections.ArrayList


class Configurator {
    private val CONFIG_MAP = WeakHashMap<Any, Any>()
    private val INTERCEPTOR_LIST = ArrayList<Interceptor>()

    companion object {
        val INSTANCE: Configurator by lazy { Configurator() }
    }

    fun getConfigMap(): WeakHashMap<Any, Any> {
        return CONFIG_MAP
    }

    fun addHostUrl(url: String): Configurator {
        CONFIG_MAP[ConfigType.HOST_URL] = url
        return this
    }

    fun addContext(context: Context): Configurator {
        CONFIG_MAP[ConfigType.CONTEXT] = context
        return this
    }

    fun addInterceptor(interceptor: Interceptor): Configurator {
        INTERCEPTOR_LIST.add(interceptor)
        CONFIG_MAP[ConfigType.INTERCEPTOR] = INTERCEPTOR_LIST
        return this
    }

    fun build() {
        CONFIG_MAP[ConfigType.IS_ALREADY] = true
    }

//    fun addWebEvent(key:String,event:Event):Configurator{
//        WebEventManager.ins.addEvent(key,event)
//        return this
//    }

    fun addInterceptors(interceptorList: ArrayList<Interceptor>): Configurator {
        INTERCEPTOR_LIST.addAll(interceptorList)
        CONFIG_MAP[ConfigType.INTERCEPTOR] = INTERCEPTOR_LIST
        return this
    }

    fun addWechatAppId(appid: String): Configurator {
        CONFIG_MAP[ConfigType.WECHAT_APPID] = appid
        return this
    }

    fun getConfig(type: ConfigType): Any? {
        if (checkIsAlready()) {
            return CONFIG_MAP[type]
        }
        throw NullPointerException("IsAlready Is False!")
    }


    private fun checkIsAlready(): Boolean {
        return when (CONFIG_MAP[ConfigType.IS_ALREADY]) {
            null -> throw NullPointerException("Please build Config!")
            true -> true
            else -> false
        }
    }
}