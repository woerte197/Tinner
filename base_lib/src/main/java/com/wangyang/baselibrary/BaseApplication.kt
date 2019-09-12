package com.wangyang.baselibrary

import android.app.Application
import com.wangyang.baselibrary.config.ModuleConfig
import com.wangyang.baselibrary.injection.component.AppComponent
import com.wangyang.baselibrary.injection.component.DaggerAppComponent
import com.wangyang.baselibrary.injection.module.AppModule

open class BaseApplication : Application() {

    companion object {
        protected lateinit var sApplication: Application
        var isDebug = true
        var appComponent: AppComponent? = null
    }


    override fun onCreate() {
        super.onCreate()
        sApplication = this
        initInjector()
        modulesApplicationInit()
        //初始化配置数据引擎
    }


    private fun initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(sApplication))
                .build()
    }

    private fun modulesApplicationInit() {
        for (moduleImpl in ModuleConfig.MODULE_LIST) {
            try {
                val clazz = Class.forName(moduleImpl)
                val obj = clazz.newInstance()
                if (obj is IApplication) {
                    obj.onCreate(this)
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }

        }
    }


}
