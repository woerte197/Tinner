package com.wangyang.baselibrary.injection.component

import android.content.Context
import com.wangyang.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun appContext(): Context
}