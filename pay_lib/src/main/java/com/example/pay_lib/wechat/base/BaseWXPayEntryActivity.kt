package com.example.pay_lib.wechat.base

import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseResp

abstract class BaseWXPayEntryActivity : BaseWXActivity() {

    companion object {
        const val WX_PAY_SUCCESS = 0
        const val WX_PAY_FAIL = 1
        const val WX_PAY_CANCEL = 2
    }

    protected abstract fun onPaySuccess()

    protected abstract fun onPayFail()

    protected abstract fun onPayCancel()

    override fun onResp(baseResp: BaseResp?) {
        if (baseResp!!.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            when (baseResp.errCode) {
                WX_PAY_CANCEL -> onPayCancel()
                WX_PAY_FAIL -> onPayFail()
                WX_PAY_SUCCESS -> onPaySuccess()
            }
        }
    }


}