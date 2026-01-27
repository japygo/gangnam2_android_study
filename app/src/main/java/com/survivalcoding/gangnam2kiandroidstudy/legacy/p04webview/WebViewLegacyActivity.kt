package com.survivalcoding.gangnam2kiandroidstudy.legacy.p04webview

import android.content.Context
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.gangnam2kiandroidstudy.R

class WebViewLegacyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_legacy)

        val webView = findViewById<WebView>(R.id.my_webview)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

//        webView.loadUrl("https://www.naver.com")
//        webView.loadUrl("file:///android_asset/index.html")
        webView.loadUrl("file:///android_asset/address.html")
    }
}

class WebAppInterface(private val mContext: Context) {

    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}