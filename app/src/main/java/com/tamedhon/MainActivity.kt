package com.tamedhon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.tamedhon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wedData: String =  "<html><body style='background-color: black; color: rgb(127 255 127);'><h1>Das Tamedhon ist nicht erreichbar!</h1><h2>Bitte überprüfe deine Internetverbindung!</h2></body></html>"
        val mimeType: String = "text/html"
        val utfType: String = "UTF-8"
        val webclient = findViewById<WebView>(R.id.webclient)
        webclient.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onReceivedError(view: WebView,
                                         request: WebResourceRequest,
                                         error: WebResourceError
            ) {
                webclient.loadData(wedData,mimeType,utfType)
            }
        }
        val settings: WebSettings = webclient.getSettings()
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true // Für localStorage / sessionStorage
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

        webclient.loadUrl("https://mud.tamedhon.de:4713/client/")

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}