package com.example.pay_lib.wechat.base

import android.content.Intent
import android.os.Bundle
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.wangyang.baselibrary.ui.activity.BaseActivity

abstract class BaseWXActivity : BaseActivity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WechatUtil.INS.WXAPI!!.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        WechatUtil.INS.WXAPI!!.handleIntent(intent, this)
    }


}