package com.example.webview_lib.webview;

import android.webkit.JavascriptInterface;

import com.example.webview_lib.webview.eventmanager.Event;
import com.example.webview_lib.webview.eventmanager.WebEventManager;

import org.json.JSONException;
import org.json.JSONObject;

public class WEcJSInterface {
    private WebActivity mWebActivity;

    private WEcJSInterface(WebActivity activity) {
        this.mWebActivity = activity;
    }

    static WEcJSInterface create(WebActivity activity) {
        return new WEcJSInterface(activity);
    }

    @JavascriptInterface
    public String event(String params) {
        try {
            JSONObject object = new JSONObject(params);
            final String action = object.getString("action");
            final Event event = WebEventManager.Companion.getIns().getEvent(action);
            event.setAction(action);
            event.setActivity(mWebActivity);
            event.setUrl(mWebActivity.getUrl());
            event.setWebView(mWebActivity.getWebView());
            return event.execute(params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
