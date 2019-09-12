package com.wangyang.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import com.wangyang.baselibrary.R
import org.jetbrains.anko.find

/*
    公告组件封装
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private val mFlipperView: ViewFlipper
    private val mImageView: ImageView

    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = rootView.find(R.id.mFlipperView)
        mImageView = rootView.find(R.id.mImageView)
        mFlipperView.setInAnimation(context, R.anim.anim_news_letter_in)
        mFlipperView.setOutAnimation(context, R.anim.anim_news_letter_out)
        addView(rootView)
    }


    /*
        构建公告
     */
    private fun buildNewsView(text: String): View {
        val view = View.inflate(context, R.layout.item_news_layout, null)
        view.findViewById<TextView>(R.id.mNewsText).text = text
        return view
    }

    /*
        设置公告数据
     */
    fun setData(data: List<String>) {
        for (text in data) {
            mFlipperView.addView(buildNewsView(text))
        }
        mFlipperView.startFlipping()
    }

    fun setImageRes(resource: Int) {
        mImageView.setImageResource(resource)
    }
}

