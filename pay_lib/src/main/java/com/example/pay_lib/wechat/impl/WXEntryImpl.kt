package com.example.pay_lib.wechat.impl

import com.example.pay_lib.wechat.base.BaseWXEntryActivity
import com.example.pay_lib.wechat.base.WechatUtil

class WXEntryImpl : BaseWXEntryActivity() {
    override fun signSuccess() {
        WechatUtil.INS.signSuccess.invoke("登录成功")
    }

    override fun onResume() {
        super.onResume()
        finish()
        overridePendingTransition(0, 0)
    }

}