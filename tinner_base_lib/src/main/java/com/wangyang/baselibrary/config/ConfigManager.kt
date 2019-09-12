package com.wangyang.baselibrary.config

import android.content.Context

class ConfigManager {
    companion object {
        fun init(context: Context): Configurator {
            return Configurator.INSTANCE.addContext(context)
        }

        fun getConfig(): Configurator {
            return Configurator.INSTANCE
        }

       fun getAppContext(): Context {
            return Configurator.INSTANCE.getConfig(ConfigType.CONTEXT) as Context
        }
    }


}