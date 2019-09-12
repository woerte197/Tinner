package com.wangyang.tinner.TEST

import android.annotation.SuppressLint
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Button
import com.wangyang.baselibrary.ui.activity.BaseActivity
import com.wangyang.tinner.R
import kotlinx.android.synthetic.main.activity_splash.*

class TestActivity : BaseActivity() {
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        val fixedThreadPool = Executors.newFixedThreadPool(3)
//        for (i in 0..9) {
//            fixedThreadPool.execute {
//                try {
//                    println(i)
//                    Thread.sleep(2000)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//        val fixedThread = Executors.newFixedThreadPool(10)
//        fixedThread.execute {
//            SystemClock.sleep(2000)
//        }
//        val cachedThread = Executors.newCachedThreadPool()
//        cachedThread.execute {
//            SystemClock.sleep(2000)
//        }
//
//
//        val scheduledThread = Executors.newScheduledThreadPool(10)
//        scheduledThread.schedule({
//            SystemClock.sleep(2000)
//        }, 2000, TimeUnit.MILLISECONDS)
//        scheduledThread.scheduleAtFixedRate({
//            SystemClock.sleep(2000)
//        }, 10, 1000, TimeUnit.MILLISECONDS)
//
//        val singleThread = Executors.newSingleThreadExecutor()
//        singleThread.execute {
//            SystemClock.sleep(2000)
//        }

        mBg.setOnClickListener {
            val button = Button(this)
            button.text = "TEST"
            val layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0,
                0,
                PixelFormat.TRANSPARENT
            )
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER
            layoutParams.gravity = Gravity.CENTER
            layoutParams.x = 100
            layoutParams.y = 200
            windowManager.addView(button, layoutParams)
            button.setOnTouchListener { v, event ->
                val mDx = event.x
                val mDY = event.y
                when(event.action){
                    MotionEvent.ACTION_MOVE->{
                        layoutParams.x = mDx.toInt()
                        layoutParams.y = mDY.toInt()
                        windowManager.updateViewLayout(button,layoutParams)
                    }
                }
                return@setOnTouchListener false



            }



        }
    }

    override fun onResume() {
        super.onResume()

    }
}