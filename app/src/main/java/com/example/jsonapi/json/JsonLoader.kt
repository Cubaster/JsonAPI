package com.example.jsonapi.json

import android.content.Context
import android.util.Log
import com.example.jsonapi.data.Comments
import com.example.jsonapi.data.Posts
import com.example.jsonapi.data.Tasks
import com.example.jsonapi.data.User
import com.example.jsonapi.database.DBHelper
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL

class JsonLoader(database: DBHelper) {
    private val urls = arrayOf("https://jsonplaceholder.typicode.com/users",
    "https://jsonplaceholder.typicode.com/todos", "https://jsonplaceholder.typicode.com/comments",
    "https://jsonplaceholder.typicode.com/posts")
    private val database = database
    private var user : User? = null
    private var task : Tasks? = null
    private var post : Posts? = null
    private var comment : Comments? = null


    // loadUsers
    fun loadUsers(){
       val response = URL(urls[0]).readText()
        if (response != "") {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                // ID
                val id = jsonArray.getJSONObject(i).getInt("id")

                // Name Surname
                val name = jsonArray.getJSONObject(i).getString("name")
                val splited: List<String> = name.split(" ")
                val fname = splited[0]
                val sname = splited[1]

                // Email
                val email = jsonArray.getJSONObject(i).getString("email")

                // Save data using your Model
                user = User(id = id, name = fname, surname = sname, email = email)
                //println(user)
                database.addUser(user!!)
            }
        }
    }

    fun loadTasks(){
        val response = URL(urls[1]).readText()
        if (response != "") {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                // User id
                val userId = jsonArray.getJSONObject(i).getInt("userId")

                // Id
                val id = jsonArray.getJSONObject(i).getInt("id")

                // Title
                val title = jsonArray.getJSONObject(i).getString("title")

                // Completed
                val completed = jsonArray.getJSONObject(i).getString("completed")

                // Save data using your Model
                task = Tasks(user = userId, id = id, title = title, completed =completed)
                //println(task)
                database.addTask(task!!)
            }
        }
    }

    fun loadPosts(){
        val response = URL(urls[3]).readText()
        if (response != "") {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                // User id
                val userId = jsonArray.getJSONObject(i).getInt("userId")

                // Id
                val postId = jsonArray.getJSONObject(i).getInt("id")

                // Title
                val title = jsonArray.getJSONObject(i).getString("title")

                // Message
                val body = jsonArray.getJSONObject(i).getString("body")

                // Save data using your Model
                post = Posts(user = userId, id = postId, title = title, body = body)
                //println(post)
                database.addPost(post!!)
            }
        }
    }

    fun loadComments(){
        val response = URL(urls[2]).readText()
        if (response != "") {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                // Post id
                val postId = jsonArray.getJSONObject(i).getInt("postId")

                // Comment id
                val id = jsonArray.getJSONObject(i).getInt("id")

                // Post name
                val name = jsonArray.getJSONObject(i).getString("name")

                // Email
                val email = jsonArray.getJSONObject(i).getString("email")

                // Response
                val body = jsonArray.getJSONObject(i).getString("body")

                // Save data using your Model
                comment = Comments(post = postId, id = id, name = name, email = email, body = body)
                //println(comment)
                database.addComment(comment!!)
            }
        }
    }
}
