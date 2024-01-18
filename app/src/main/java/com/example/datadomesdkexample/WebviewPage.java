package com.example.datadomesdkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewPage extends AppCompatActivity {

    private String domain = "your_domain";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_page);

        WebView webview = findViewById(R.id.webviewPage);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().getJavaScriptEnabled();
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        CookieManager.getInstance().removeAllCookie();
        String url = "testing_url";
        String cookie = MainActivity.dataDomeSdk.getCookie();
        CookieManager.getInstance().setCookie(domain, "datadome="+cookie);
        webview.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String cookies = CookieManager.getInstance().getCookie(domain);
        String datadomeCookie = filterDatadomeCookie(cookies);
        MainActivity.dataDomeSdk.setCookie(datadomeCookie);
    }

    // Retrieve Datadome cookie from CookieManager cookies
    private String filterDatadomeCookie(String cookie) {
        String cookieName = "datadome=";
        String[] cookieSplit = cookie.split(cookieName);
        if (cookieSplit.length > 1) {
            if (cookieSplit[1].contains(";")) {
                return cookieName+cookieSplit[1].split(";")[0];
            }
            return cookieName+cookieSplit[1];
        }
        return "";
    }
}