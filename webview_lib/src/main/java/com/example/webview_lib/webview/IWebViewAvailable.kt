package com.example.webview_lib.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

interface IWebViewAvailable {
    fun initWebView(webView: WebView): WebView

    fun initWebChromeClient(): WebChromeClient

    fun initWebViewClient(): WebViewClient

}
