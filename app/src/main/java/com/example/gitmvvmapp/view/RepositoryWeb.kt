package com.example.gitmvvmapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.gitmvvmapp.R

class RepositoryWeb : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_web)

        val intent = intent

        val repoWeb = findViewById<WebView>(R.id.repository_webview)

        repoWeb.loadUrl(intent.getStringExtra("url")
        )
    }
}
