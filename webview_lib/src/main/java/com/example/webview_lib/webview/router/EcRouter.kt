package com.example.webview_lib.webview.router

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.webkit.WebView

import com.example.webview_lib.webview.LastWebUtil
import com.example.webview_lib.webview.WebActivity
import com.example.webview_lib.webview.WebActivityImpl
import com.example.webview_lib.webview.weburltype.WebUrlType


class EcRouter {


    fun handleUrl(url: String, activity: WebActivity): Boolean {
        if (url.contains("tel:")) {
            callPhone(url, activity)
        }
        val topActivity = LastWebUtil.ins.activity
        val intent = Intent(topActivity, WebActivityImpl::class.java)
        intent.putExtra(WebUrlType.WEB_URL_TYPE, url)
        topActivity.startActivity(intent)
        return true
    }


    fun loadPage(activity: WebActivity, url: String) {
        loadPage(activity.webView, url)
    }

    private fun loadPage(webView: WebView, url: String) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebUrl(webView, url)
        } else {
            loadLocalUrl(webView, url)
        }
    }

    private fun loadLocalUrl(webView: WebView, url: String) {
        val localUrl = "file:///android_asset/$url"
        webView.loadUrl(localUrl)
    }

    private fun loadWebUrl(webView: WebView, url: String) {
        webView.loadUrl(url)
    }

    private fun callPhone(url: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse(url)
        intent.data = data
        activity.startActivity(intent)
    }

    companion object {
        val ins: EcRouter by lazy { EcRouter() }
    }

}
