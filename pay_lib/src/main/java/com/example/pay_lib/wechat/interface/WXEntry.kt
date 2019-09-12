package com.example.pay_lib.wechat.`interface`

import com.example.annotations.EntryGenerator
import com.example.pay_lib.wechat.impl.WXEntryImpl

@SuppressWarnings("unused")
@EntryGenerator(packageName = "com.example.wangyang", entryTemplate = WXEntryImpl::class)
interface WXEntry