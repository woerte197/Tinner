package com.example.webview_lib.webview

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView

object EcWebViewInit {


    @SuppressLint("NewApi", "SetJavaScriptEnabled")
    fun initWebView(webView: WebView): WebView {
        WebView.setWebContentsDebuggingEnabled(true)
        //不能横向滑动
        webView.isHorizontalScrollBarEnabled = false
        //不能够纵向互动
        webView.isVerticalScrollBarEnabled = false
        //允许截图
        webView.isDrawingCacheEnabled = true
        //屏蔽长按事件
        webView.setOnLongClickListener { v -> true }
        //初始化WebSetting
        val settings = webView.settings
        val ua = settings.userAgentString
        settings.javaScriptEnabled = true
        settings.userAgentString = ua + "Latte"
        //隐藏缩放控件
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        //禁止缩放
        settings.setSupportZoom(false)
        //文件权限
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
        //缓存相关
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        return webView
    }


}
