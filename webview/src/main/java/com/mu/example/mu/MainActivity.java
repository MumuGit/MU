package com.mu.example.mu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mWebView= (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView .getSettings();
        //支持获取手势焦点，输入用户名、密码或其他
        webSettings.setUseWideViewPort(true);
        mWebView.loadUrl("http://www.baidu.com/");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

    }
}
