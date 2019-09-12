package com.example.pay_lib.wechat.base

import android.annotation.SuppressLint
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.wangyang.baselibrary.ext.execute
import com.wangyang.baselibrary.net.client.MyClient
import com.wangyang.baselibrary.net.observable.BaseSubscribe

abstract class BaseWXEntryActivity : BaseWXActivity() {


    override fun onResp(baseResp: BaseResp?) {
        val code = (baseResp as SendAuth.Resp).code
        val authUrl = StringBuilder()

        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(WechatUtil.WECHAT_APP_ID)
                .append("&secret=")
                .append(WechatUtil.WECHAT_APP_ID)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code")
        getAuth(authUrl.toString())
    }

    @SuppressLint("CheckResult")
    private fun getAuth(url: String) {
        MyClient.run {
            builder()
                    .addUrl(url)
                    .build()
                    .get()
                    .execute(object : BaseSubscribe<String>() {
                        override fun onNext(t: String) {
                            getUserInfo("url")
                        }
                    })
        }
    }

    override fun onReq(p0: BaseReq?) {
    }

    fun getUserInfo(userInfoUrl: String) {

        MyClient.builder()
                .addUrl(userInfoUrl)
                .build()
                .get()
                .execute(object : BaseSubscribe<String>() {
                    override fun onNext(t: String) {
                        signSuccess()
                    }
                })
    }

    abstract fun signSuccess()
}