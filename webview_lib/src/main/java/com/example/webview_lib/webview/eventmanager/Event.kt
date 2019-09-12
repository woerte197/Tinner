package com.example.webview_lib.webview.eventmanager

import android.webkit.WebView

import com.example.webview_lib.webview.WebActivity


abstract class Event : IEvent {
    var action: String? = null
    var activity: WebActivity? = null
    var webView: WebView? = null
    var url: String? = null
}
