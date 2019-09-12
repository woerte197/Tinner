package com.example.webview_lib.webview


import com.wangyang.baselibrary.ui.activity.BaseActivity

class LastWebUtil {

    private var mActivity: BaseActivity? = null

    var activity: BaseActivity
        get() = mActivity ?: throw NullPointerException("BaseActivity Is Not Be Null!")
        set(mActivity) {
            this.mActivity = mActivity
        }


    fun releaseActivity() {
        if (mActivity != null) {
            mActivity = null
        }
    }

    companion object {

        val ins: LastWebUtil by lazy { LastWebUtil() }
    }

}
