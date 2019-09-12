package com.example.webview_lib.webview.webviewclient

import android.webkit.WebView
import android.webkit.WebViewClient

import com.example.webview_lib.webview.WebActivity
import com.example.webview_lib.webview.router.EcRouter

class EcWebViewClient(private val mActivity: WebActivity) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return EcRouter.ins.handleUrl(url, mActivity)
    }
}
