package com.wangyang.tinner.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.wangyang.tinner.R
import com.wangyang.tinner.dagger.compontent.DaggerSplashComment
import com.wangyang.tinner.presenter.SplashPresenter
import com.wangyang.tinner.presenter.view.SplashView
import com.wangyang.tinner_lib.StartsBarUtil
import com.wangyang.tinner_lib.ui.TinnerActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : TinnerActivity<SplashPresenter>(), SplashView {
    override fun inject() {
        DaggerSplashComment.builder().build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        StartsBarUtil.setFullScreenWindowLayout(window)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSplashView.startCountdown()
        mPresenter.loadImage(mBg)
        mSplashView.setOnClickListener {
            mPresenter.login()
        }
        mSplashView.listener = {
            mPresenter.login()
        }
    }
}
