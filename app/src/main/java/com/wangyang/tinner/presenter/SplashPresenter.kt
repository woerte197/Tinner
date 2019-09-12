package com.wangyang.tinner.presenter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.wangyang.baselibrary.presenter.BasePresenter
import com.wangyang.tinner.presenter.view.SplashView
import com.wangyang.tinner.ui.MainActivity
import javax.inject.Inject

class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {

    fun login() {
        val intent = Intent()
        intent.setClass(mView as Context, MainActivity::class.java)
        (mView as Context).startActivity(intent)
    }

    fun loadImage(mBg: AppCompatImageView) {
        Glide.with(mView as Context)
            .load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1402842141,2294732522&fm=26&gp=0.jpg")
            .into(mBg)
    }

}