package com.wangyang.baselibrary.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

abstract class BaseDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context,setStyle())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 设置Content前设定
        dialog.setCanceledOnTouchOutside(true) // 外部点击取消
        val window = dialog.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = window.attributes
        lp.gravity = Gravity.CENTER
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = layoutInflater.inflate(setLayout(), container, false)
        initView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initBundle(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
    }

    abstract fun initBundle(savedInstanceState: Bundle?)


    abstract fun setStyle(): Int

    abstract fun initView(view: View)


    abstract fun setLayout(): Int

}