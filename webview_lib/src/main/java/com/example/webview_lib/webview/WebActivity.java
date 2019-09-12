package com.example.webview_lib.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.wangyang.baselibrary.ui.activity.BaseActivity;
import com.example.webview_lib.webview.weburltype.WebUrlType;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public  abstract class WebActivity extends BaseActivity {

    private WebView mWebView = null;

    private String mUrl = null;

    private boolean mIsWebViewAvailable = false;

    private Activity mTopActivity = null;


    private final ReferenceQueue<WebView> REFERENCE_QUEUE = new ReferenceQueue<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
        initWebUrl();
        setContentView(setLayout());
    }

    protected abstract View setLayout();


    protected void initWebUrl() {
        mUrl = getIntent().getStringExtra(WebUrlType.WEB_URL_TYPE);
    }

    protected abstract IWebViewAvailable initIWebViewAvailable();


    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            IWebViewAvailable available = initIWebViewAvailable();
            if (available != null) {
                final WeakReference<WebView> weakReference = new WeakReference<>(new WebView(this), REFERENCE_QUEUE);
                mWebView = weakReference.get();
                //初始化WebView
                available.initWebView(mWebView);
                mWebView.setWebChromeClient(available.initWebChromeClient());
                mWebView.setWebViewClient(available.initWebViewClient());
                //增加与js交互
                mWebView.addJavascriptInterface(WEcJSInterface.create(this), "WJS");
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("IWebViewAvailable Is Be Null!");
            }
        }
    }

    public WebView getWebView() {
        if (mWebView != null && mIsWebViewAvailable) {
            return mWebView;
        } else {
            throw new NullPointerException("WebView Is Not Be Null!");
        }
    }

    protected String getUrl() {
        if (mUrl != null) {
            return mUrl;
        } else {
            throw new NullPointerException("Url Is Not Be Null!");
        }
    }

    public void setTopActivity(Activity mTopActivity) {
        this.mTopActivity = mTopActivity;
    }

    public Activity getTopActivity() {
        if (mTopActivity != null) {
            return mTopActivity;
        } else {
            throw new NullPointerException("TopActivity Is Not Be Null!");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
        if (mTopActivity != null) {
            mTopActivity = null;
        }
    }


}

