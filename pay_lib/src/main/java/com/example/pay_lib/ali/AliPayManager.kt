package com.example.pay_lib.ali

import android.app.Activity
import com.alipay.sdk.app.PayTask
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object AliPayManager {


    fun aliPay(orderInfo: String, activity: Activity, aliResult: IAliPayResult) {
        doAsync {
            val result = PayTask(activity).payV2(orderInfo, true)
            uiThread {
                when (result["resultStatus"]) {
                    "9000" -> {
                        aliResult.result("支付成功")
                    }
                }
            }
        }
    }

    interface IAliPayResult {
        fun result(msg: Any)
    }
}