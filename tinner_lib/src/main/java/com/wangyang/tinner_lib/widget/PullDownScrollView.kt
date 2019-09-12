package com.wangyang.tinner_lib.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.tencent.mm.opensdk.utils.Log
import com.wangyang.tinner_lib.common.TransAnimationListener


class PullDownScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {
    var mToolBarHeight: Int = 200
    var mScrollY: Int = 0
    private var mIsHeaderPull: Boolean = true
    var mIsHeaderAnim: Boolean = false
    var mOldY = 0
    var mDY = 0
    var mTransY = 0f
    var mTopViewHeight = 0f
    var mIsShowImageView = false
    var listener: (alpha: Float, scrollY: Int) -> Unit = { _: Float, _: Int -> }
    var loadImageView: () -> Unit = {}
    var loadTextView: (text: String) -> Unit = {}
    var loadImageViewAnim: (anim: Float) -> Unit = {}

    private var mScreenHeight: Int = 0
    private var isonce: Boolean = false//加载该View的布局时是否是第一次加载，是第一次就让其实现OnMeasure里的代码
    var mTopView: ViewGroup? = null
    var imageViewCancel: () -> Unit = {}

    init {
        val wm = getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics) //获取描述该显示的大小和密度的显示指标
        mScreenHeight = metrics.heightPixels  //用显示器的高度
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isonce) {
            val mParentView = this.getChildAt(0) as LinearLayout
            mTopView = mParentView.getChildAt(0) as ViewGroup
            mTopView?.post {
                mTopViewHeight = mTopView?.height!!.toFloat()
            }
            isonce = true
        }
    }

    override fun onScrollChanged(scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY)
        if (!mIsHeaderAnim) {
            val alpha = when {
                scrollY > mToolBarHeight -> 255f
                scrollY == 0 -> 0f
                else -> String.format("%.2f", (scrollY.toFloat() / mToolBarHeight)).toFloat() * 255f
            }
            mScrollY = scrollY
            listener.invoke(alpha, scrollY)
            mTopView?.translationY = (scrollY / 1.5).toFloat()
        }
//        mIsHeaderPull = false //开启判断是否从头部下拉
    }


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        super.onTouchEvent(ev)
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("ACTION_DOWN", scaleY.toString())
                mOldY = ev.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                mDY = ev.y.toInt()
                if (mScrollY == 0) {
                    scrollTo(0, 0)
                    if (mDY - mOldY in 50..399) {
                        loadTextView.invoke("下拉加载壁纸")
                        mIsShowImageView = false
                    } else if (mDY - mOldY > 398) {
                        loadTextView.invoke("松手显示壁纸壁纸")
                        mIsShowImageView = true
                    }
                    Log.e("ACTION_MOVE", mOldY.toString())

                    if (mDY > mOldY) {
                        mTransY = String.format("%.2f", ((mDY - mOldY).toFloat() / 10)).toFloat()
                        var scrollY = String.format("%.2f", ((mDY - mOldY).toFloat() / 10)).toFloat()
                        Log.e("ACTION_MOVE", scrollY.toString())
                        mIsHeaderAnim = true
                        loadImageViewAnim.invoke(scrollY)
//                        loadTopViewAnim()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.e("ACTION_UP", scaleY.toString())
                if (mScrollY == 0 && mIsShowImageView && mIsHeaderPull) {
                    loadImageView.invoke()
                    mIsShowImageView = false
                }
                mIsHeaderPull = true
                mIsHeaderAnim = false
                imageViewCancel.invoke()
//                topViewCancel()//代码整理
            }
            MotionEvent.ACTION_CANCEL -> {
                if (mScrollY == 0 && mIsShowImageView && mIsHeaderPull) {
                    loadImageView.invoke()
                    mIsShowImageView = false
                }
                imageViewCancel.invoke()
//                topViewCancel()//代码整理
                mIsHeaderAnim = false
                mIsHeaderPull = true
            }

        }
        return true
    }

    private fun loadTopViewAnim() {
        Log.e("loadImageViewAnimmTransY", mTransY.toString())
        mTopView?.layoutParams?.height = ((mTopViewHeight + mTransY).toInt())
        Log.e("loadImageViewAnim", (mTopViewHeight + mTransY).toString())
        mTopView?.requestLayout()
    }

    private fun topViewCancel() {
        var objectAnimator: ValueAnimator = ObjectAnimator.ofFloat(mTransY, 0f)
        objectAnimator.duration = 200
        objectAnimator.addUpdateListener {
            Log.e("loadImageViewAnimAAAAA", (it.animatedValue as Float?).toString())
            mTopView?.layoutParams!!.height = ((mTopViewHeight + it.animatedValue as Float).toInt())
            mTopView?.requestLayout()
        }
        objectAnimator.start()
    }


    private fun createAnimation(anim: Int, view: View, duration: Long): Animation {
        val animation = AnimationUtils.loadAnimation(context, anim)
        animation.duration = duration
        animation.setAnimationListener(object : TransAnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                view.isEnabled = true
            }

            override fun onAnimationStart(animation: Animation?) {
                view.isEnabled = false
            }

        })
        return animation
    }

}