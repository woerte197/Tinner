package com.wangyang.tinner_lib.widget

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.wangyang.tinner_lib.R
import org.jetbrains.anko.backgroundResource
import java.util.logging.Handler as Handler1

class SplashView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {
    private var mTimer: Int
    private var mHandler: Handler
    var listener: () -> Unit = {

    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SplashView)
        mTimer = typedArray.getInteger(R.styleable.SplashView_timer, 3)
        typedArray.recycle()
        text = "跳过$mTimer S"
        gravity = Gravity.CENTER
        mHandler = Handler()
    }

     fun startCountdown() {
        mHandler.postDelayed(countDown,0)
    }

    private val countDown = object : Runnable {
        override fun run() {
            this@SplashView.text = "跳过$mTimer S"
            if (mTimer > 0) {
                this@SplashView.text = "跳过$mTimer S"
                mHandler.postDelayed(this, 1200)
            } else {
                listener.invoke()
            }
            mTimer--
        }
    }


}