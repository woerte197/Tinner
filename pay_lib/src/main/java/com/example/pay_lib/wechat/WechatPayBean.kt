package com.example.pay_lib.wechat

import com.google.gson.annotations.SerializedName

data class WechatPayBean (val appid:String,val partnerid:String,val prepayid:String,val noncestr:String,val timestamp:String, @SerializedName("package")
val packages :String,val sign:String,val extData:String = "extData"){}