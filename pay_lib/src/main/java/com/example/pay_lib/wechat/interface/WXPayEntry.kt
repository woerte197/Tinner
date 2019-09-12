package com.example.pay_lib.wechat.`interface`

import com.example.annotations.PayEntryGenerator
import com.example.pay_lib.wechat.impl.PayWXEntryImpl

@SuppressWarnings("unused")
@PayEntryGenerator(packageName = "com.example.wangyang",payEntryTemplate = PayWXEntryImpl::class )

interface WXPayEntry {

}