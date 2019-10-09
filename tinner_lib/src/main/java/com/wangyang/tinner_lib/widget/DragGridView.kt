package com.wangyang.tinner_lib.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.GridView


class DragGridView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GridView(context, attrs, defStyleAttr), AdapterView.OnItemLongClickListener {

    //手指落下时x的坐标（相对于整个屏幕）
    private var mWindowX = 0f
    //手指落下时y的坐标（相对于整个屏幕）
    private var mWindowY = 0f
    //windowManager
    private var mWindowManager: WindowManager? = null
    //当前View
    private var currentView: View? = null
    //拖动的View
    private var dragView: View? = null
    //临时Position
    private var tempPosition: Int = 0
    //当前Position
    private var currentPosition: Int = 0

    //view 的x 差值
    private var mX: Float = 0f
    //view 的y 差值
    private var mY: Float = 0f

    companion object {
        private val MODE_DRAG = 1
        private val MODE_NORMAL = 2
    }

    private var mode = MODE_NORMAL

    init {
        mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        onItemLongClickListener = this

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
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
        //给出的空间是父组件最大的空间
        val expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)

    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {
                updateWindow(ev)

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return super.onTouchEvent(ev)

    }

    private fun updateWindow(ev: MotionEvent) {


    }


    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        if (mode == MODE_DRAG) {
            return false
        }

        this.currentView = view
        this.currentPosition = position
        this.tempPosition = position
        mX = mWindowX - view!!.left - this.left
        mY = mWindowY - view!!.top - this.top
        if (Build.VERSION.SDK_INT >= 23) {
//            if (Settings.canDrawOverlays(context)) {
            initWindow()
//            } else {
//                // 跳转到悬浮窗权限管理界面
//                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
//                context.startActivity(intent)
//            }
        } else {
            // 如果小于Android 6.0 则直接执行
            initWindow()
        }



        return true
    }

    private fun initWindow() {

    }

}