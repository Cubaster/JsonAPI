package com.example.jsonapi

import android.os.Bundle
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.jsonapi.adapters.UserAdapter
import com.example.jsonapi.database.DBHelper
import com.example.jsonapi.json.JsonLoader


class MainActivity : AppCompatActivity() {
    private var jsonload : JsonLoader? = null
    private var database : DBHelper? = null
    private var loaded : Boolean = false
    private var adapter : UserAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.userList)
        val loading = findViewById<ProgressBar>(R.id.loading)

        database = DBHelper(applicationContext)
        jsonload = JsonLoader(database!!)
        getRecord()


        val thread = Thread() {
            run {
                loading.isVisible = true
                if (!loaded){
                    jsonload!!.loadUsers()
                    jsonload!!.loadTasks()
                    jsonload!!.loadPosts()
                    jsonload!!.loadComments()
                    loaded = true
                    setRecord()
                }
                Thread.sleep(1000)
            }
            runOnUiThread() {
                loading.isVisible = false
                Toast.makeText(applicationContext, "Files in database", Toast.LENGTH_SHORT).show()
                adapter = UserAdapter(applicationContext, database!!.listUsers())
                listView.adapter = adapter
            }
        }
        thread.start()

    }

    // keeps status of loading json file
    private fun setRecord(){
        val shared = this.getSharedPreferences("com.example.myapplication.shared",0)
        val edit = shared.edit()
        edit.putBoolean("state", loaded)
        edit.apply()
    }

    // keeps status of loading json file
    private fun getRecord(){
        val shared = this.getSharedPreferences("com.example.myapplication.shared",0)
        loaded = if(shared.contains("state"))
            shared.getBoolean("state", false)
        else
            false
    }
}