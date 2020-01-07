package com.bhsoft.rssreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class NewDetailActivity extends AppCompatActivity {
WebView wv;
String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        wv=findViewById(R.id.webView1);
        link = getIntent().getExtras().getString("link");

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        wv.loadUrl(link);
        }
    }
