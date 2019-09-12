package com.wangyang.baselibrary.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.wangyang.baselibrary.R

/*
    获取验证码按钮
    带倒计时
 */
class VerifyButton(mContext: Context, attrs: AttributeSet) : AppCompatButton(mContext, attrs) {
    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: OnVerifyBtnClick? = null

    init {
        this.text = "获取验证码"
        mHandler = Handler()

    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        @SuppressLint("ResourceAsColor")
        override fun run() {
            this@VerifyButton.text = "$mCount s"
            this@VerifyButton.setBackgroundColor(R.color.common_disable)
            this@VerifyButton.setTextColor(R.color.common_white)
            this@VerifyButton.isEnabled = false

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    /*
        恢复到初始状态
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(resources.getColor(R.color.transparent))
        this.setTextColor(resources.getColor(R.color.common_blue))
        mCount = 60
    }

    /*
        点击事件接口
     */
    interface OnVerifyBtnClick {
        fun onClick()
    }

    fun setOnVerifyBtnClick(onVerifyBtnClick: OnVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}
