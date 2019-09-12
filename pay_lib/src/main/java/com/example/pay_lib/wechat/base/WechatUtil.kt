package com.example.pay_lib.wechat.base

import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.wangyang.baselibrary.config.ConfigManager
import com.wangyang.baselibrary.config.ConfigType

class WechatUtil {

    companion object {
        val INS: WechatUtil by lazy { WechatUtil() }
        val WECHAT_APP_ID: String? = ConfigManager.getConfig().getConfig(ConfigType.WECHAT_APPID) as String
    }

    val WXAPI: IWXAPI?

    init {
        WXAPI = WXAPIFactory.createWXAPI(ConfigManager.getAppContext(), WECHAT_APP_ID)
        WXAPI.registerApp(WECHAT_APP_ID)
    }

    lateinit var signSuccess: ((String) -> Unit)


    fun signIn() {
        val req = SendAuth.Req()
        req.scope = "snsapi_userinfo"
        req.state = "random_state"
        WXAPI!!.sendReq(req)
    }

    fun wechatPay(req: PayReq) {
        WXAPI!!.sendReq(req)
    }

}