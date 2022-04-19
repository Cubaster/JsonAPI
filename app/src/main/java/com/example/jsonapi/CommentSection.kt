package com.example.jsonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.example.jsonapi.adapters.CommentAdapter
import com.example.jsonapi.database.DBHelper

class CommentSection : AppCompatActivity() {
    private lateinit var listView: ListView
    private var database : DBHelper? = null
    private var adapter : CommentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_section)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        val title = findViewById<TextView>(R.id.name)
        val body = findViewById<TextView>(R.id.body)
        listView = findViewById<ListView>(R.id.commentList)

        val b = intent.extras
        title.text = "${b!!.getString("title")}"
        body.text = "${b.getString("body")}"
        database = DBHelper(applicationContext)

        adapter = CommentAdapter(applicationContext, database!!.listComments(b.getInt("id")))
        listView.adapter = adapter

        backButton.setOnClickListener{
            finish()
        }
    }
}