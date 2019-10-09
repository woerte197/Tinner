package com.wangyang.tinner_lib.widget

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.provider.Settings
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import com.wangyang.tinner_lib.R


class DragGridView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GridView(context, attrs, defStyleAttr), AdapterView.OnItemLongClickListener {

    //手指落下时x的坐标（相对于整个屏幕）
    private var mWindowX = 0f
    //手指落下时y的坐标（相对于整个屏幕）
    private var mWindowY = 0f
    //windowManager
    private val mWindowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    //当前View
    private var currentView: View? = null
    //拖动的View
    private var dragView: View? = null
    //临时Position
    private var tempPosition: Int = 0
    //当前Position
    private var currentPosition: Int = 0

    private var layoutParams: WindowManager.LayoutParams? = null

    //view 的x 差值
    private var mX: Float = 0f
    //view 的y 差值
    private var mY: Float = 0f

    companion object {
        private const val MODE_DRAG = 1
        private const val MODE_NORMAL = 2
    }

    private var mode = MODE_NORMAL

    init {
        onItemLongClickListener = this

    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mWindowX = ev.rawX
                mWindowY = ev.rawY
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.onInterceptTouchEvent(ev)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = View.MeasureSpec.makeMeasureSpec(
            Integer.MAX_VALUE shr 2,
            View.MeasureSpec.AT_MOST
        )
        super.onMeasure(widthMeasureSpec, expandSpec)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {
                if (mode == MODE_DRAG) {
                    updateWindow(ev)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mode == MODE_DRAG) {
                    closeWindow(ev.x, ev.y)
                }

            }
        }
        return super.onTouchEvent(ev)

    }

    private fun closeWindow(x: Float, y: Float) {
        if (dragView != null) {
            mWindowManager?.removeView(dragView)
            dragView = null
            layoutParams = null
        }
        itemDrop()
        mode = MODE_NORMAL
    }

    private fun itemDrop() {
        if (tempPosition == currentPosition || tempPosition == INVALID_POSITION) {
            getChildAt(currentPosition).visibility = View.VISIBLE
        } else {
            val adapter = adapter
            if (adapter != null && adapter is DragGridAdapter<*>) {
                adapter.exchangePosition(currentPosition, tempPosition, false)
            }
        }
    }

    private fun updateWindow(ev: MotionEvent) {
        if (mode == MODE_DRAG) {
            var x = ev.rawX - mX
            var y = ev.rawY - mY
            if (layoutParams != null) {
                layoutParams?.x = x.toInt()
                layoutParams?.y = y.toInt()
                mWindowManager?.updateViewLayout(dragView, layoutParams)
            }
            var mx = ev.x
            var my = ev.y
            //获取到移动地方的position
            var dropPosition = pointToPosition(mx.toInt(), my.toInt())
            if (dropPosition == tempPosition || dropPosition == INVALID_POSITION) {
                return
            }
            itemMove(dropPosition)
        }



    }

    private fun itemMove(dropPosition: Int) {
        var translateAnimation: TranslateAnimation
        if (dropPosition < tempPosition) {
            for (i in dropPosition until tempPosition) {
                val view = getChildAt(i)
                val nextView = getChildAt(i + 1)
                val xValue = (nextView.left - view.left) * 1f / view.width
                val yValue = (nextView.top - view.top) * 1f / view.height
                translateAnimation = TranslateAnimation(
                    RELATIVE_TO_SELF,
                    0f,
                    RELATIVE_TO_SELF,
                    xValue,
                    RELATIVE_TO_SELF,
                    0f,
                    RELATIVE_TO_SELF,
                    yValue
                )
                translateAnimation.interpolator = LinearInterpolator()
                translateAnimation.fillAfter = true
                translateAnimation.duration = 300
                if (i == tempPosition - 1) {
                    translateAnimation.setAnimationListener(animationListener)
                }
                view.startAnimation(translateAnimation)
            }
        } else {
            for (i in tempPosition + 1..dropPosition) {
                var view = getChildAt(i)
                var prevView = getChildAt(i - 1)
                var xValue = (prevView.left - view.left) * 1f / view.width
                var yValue = (prevView.top - view.top) * 1f / view.height
                translateAnimation = TranslateAnimation(
                    RELATIVE_TO_SELF,
                    0f,
                    RELATIVE_TO_SELF,
                    xValue,
                    RELATIVE_TO_SELF,
                    0f,
                    RELATIVE_TO_SELF,
                    yValue
                )
                translateAnimation.fillAfter = true
                translateAnimation.duration = 300
                if (i == dropPosition) {
                    translateAnimation.setAnimationListener(animationListener);
                }
                view?.startAnimation(translateAnimation)
            }
        }
        tempPosition = dropPosition
    }

    var animationListener: Animation.AnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {

        }

        override fun onAnimationEnd(animation: Animation) {
            // 在动画完成时将adapter里的数据交换位置
            val adapter = adapter
            if (adapter != null && adapter is DragGridAdapter<*>) {
                adapter.exchangePosition(currentPosition, tempPosition, true)
            }
            currentPosition = tempPosition
        }

        override fun onAnimationRepeat(animation: Animation) {

        }
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View, position: Int, id: Long): Boolean {
        if (mode == MODE_DRAG) {
            return false
        }
        this.currentView = view
        this.currentPosition = position
        this.tempPosition = position
        mX = mWindowX - view.left.toFloat() - this.left.toFloat()
        mY = mWindowY - view.top.toFloat() - this.top.toFloat()
        // 如果是Android 6.0 要动态申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(context)) {
                initWindow()
            } else {
                // 跳转到悬浮窗权限管理界面
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                context.startActivity(intent)
            }
        } else {
            // 如果小于Android 6.0 则直接执行
            initWindow()
        }
        return true



        return true
    }

    private fun initWindow() {
        if (dragView == null) {
            dragView = View.inflate(context, R.layout.item_drag, null)
            val tv_text = dragView!!.findViewById<View>(R.id.item) as TextView
            tv_text.text = (currentView!!.findViewById<View>(R.id.item) as TextView).text
        }
        if (layoutParams == null) {
            layoutParams = WindowManager.LayoutParams()
            layoutParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
            layoutParams!!.format = PixelFormat.RGBA_8888
            layoutParams!!.gravity = Gravity.TOP or Gravity.LEFT
            layoutParams!!.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等
            layoutParams!!.width = currentView!!.width
            layoutParams!!.height = currentView!!.height
            layoutParams!!.x = currentView!!.left + this.left  //悬浮窗X的位置
            layoutParams!!.y = currentView!!.top + this.top  //悬浮窗Y的位置
            currentView!!.visibility = View.INVISIBLE
        }

        mWindowManager?.addView(dragView, layoutParams)
        mode =MODE_DRAG
    }



}