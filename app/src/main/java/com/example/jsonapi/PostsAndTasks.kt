package com.example.jsonapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jsonapi.adapters.PostAdapter
import com.example.jsonapi.adapters.TaskAdapter
import com.example.jsonapi.database.DBHelper

class PostsAndTasks : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var listView2: ListView
    private var database : DBHelper? = null
    private var adapter : TaskAdapter? = null
    private var adapter2 : PostAdapter? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_and_tasks)

        // elements
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val author = findViewById<TextView>(R.id.userContent)
        listView = findViewById<ListView>(R.id.taskList)
        listView2 = findViewById<ListView>(R.id.postList)

        // display author
        val b = intent.extras
        author.text = "${b!!.getString("name")}'s content"
        database = DBHelper(applicationContext)

        adapter = TaskAdapter(applicationContext, database!!.listTasks(b.getInt("id")))
        listView.adapter = adapter
        adapter2 = PostAdapter(applicationContext, database!!.listPosts(b.getInt("id")))
        listView2.adapter = adapter2

        backButton.setOnClickListener{
            finish()
        }
    }
}