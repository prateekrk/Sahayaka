package com.academicbot.sahayaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class webview extends AppCompatActivity {
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        web=findViewById(R.id.web);
        web.loadUrl("https://www.mohfw.gov.in/");
    }
}
