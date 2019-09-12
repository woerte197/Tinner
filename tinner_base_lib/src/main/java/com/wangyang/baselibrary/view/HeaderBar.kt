package com.wangyang.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wangyang.baselibrary.R
import kotlinx.android.synthetic.main.layout_header_bar.view.*

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mIsShowBack = true
    private var mTitleText: String? = null
    private var mRightText: String? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        mIsShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, false)
        mTitleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        mRightText = typedArray.getString(R.styleable.HeaderBar_rightText)
        initView()
        typedArray.recycle()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)
        mLeftIv.visibility = if (mIsShowBack) View.VISIBLE else View.GONE
        mTitleText?.let {
            mTitleTv.text = it
        }
        mRightText?.let {
            mRightTv.text = it
        }
    }

}