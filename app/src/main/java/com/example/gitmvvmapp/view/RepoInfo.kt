package com.example.gitmvvmapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gitmvvmapp.R
import kotlinx.android.synthetic.main.activity_repo_info.*

class RepoInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)
        val intent = intent
        val repoName = findViewById<TextView>(R.id.repo_name_textview)
        val notifyText = findViewById<TextView>(R.id.notify_textview)
        val repoUrl = findViewById<TextView>(R.id.repo_url_textview)


        val avatarUrl: String = intent.getStringExtra("avatar")
        repoName.text = intent.getStringExtra("name")
        repoUrl.text =  intent.getStringExtra("url")
        notifyText.text = "Please click on the link below to open the repository!"

        repoUrl.setOnClickListener {
            val intent = Intent(this, RepositoryWeb::class.java)
            intent.putExtra("url",repoUrl.text.toString())
            startActivity(intent)
        }

        Glide.with(this)
            .load(avatarUrl)
            .into(avatar_imageview)

    }
}
