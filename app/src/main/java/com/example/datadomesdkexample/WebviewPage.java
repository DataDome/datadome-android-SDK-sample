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
        webview.getSettings().setUserAgentString("BLOCKUA");
        String url = "testing_url";
        String cookie = MainActivity.dataDomeSdk.getCookie();
        CookieManager.getInstance().setCookie(domain, cookie);
        webview.loadUrl(url);
        String wvCookie = CookieManager.getInstance().getCookie(domain);
        Log.d("cookie value", "webview cookie value: "+wvCookie);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.dataDomeSdk.setCookie("cookie to inject into MainActivity");
    }
}