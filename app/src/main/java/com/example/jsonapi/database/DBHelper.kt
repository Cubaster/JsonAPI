package com.example.jsonapi.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.jsonapi.data.Comments
import com.example.jsonapi.data.Posts
import com.example.jsonapi.data.Tasks
import com.example.jsonapi.data.User

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VER) {
    companion object{
        private const val DATABASE_VER = 2
        private const val DATABASE_NAME = "json.db"
        // Table 1
        private const val USER_TABLE = "Users"
        private const val KEY_ID = "Id"
        private const val KEY_NAME = "Name"
        private const val KEY_SURNAME = "Surname"
        private const val KEY_EMAIL = "Email"
        private const val KEY_TCOUNT = "TaskCount"
        private const val KEY_PCOUNT = "PostCount"
        // Table 2
        private const val TASK_TABLE = "Tasks"
        private const val KEY_USERID = "UserId"
        private const val KEY_TASKID = "TaskId"
        private const val KEY_TITLE = "Title"
        private const val KEY_COMPLETED = "Completed"
        // Table 3
        private const val POST_TABLE = "Posts"
        private const val KEY_USER = "UserId"
        private const val KEY_POSTID = "PostId"
        private const val KEY_PTITLE = "Title"
        private const val KEY_PBODY = "Message"
        // Table 4
        private const val COMMENT_TABLE = "Comments"
        private const val KEY_POST = "PostId"
        private const val KEY_COMMENTID = "CommentId"
        private const val KEY_CNAME = "Name"
        private const val KEY_CEMAIL = "Email"
        private const val KEY_CBODY = "Response"
    }

    // create database
    override fun onCreate(database: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY1 = ("CREATE TABLE $USER_TABLE ($KEY_ID PRIMARY KEY, $KEY_NAME TEXT, $KEY_SURNAME TEXT, $KEY_EMAIL TEXT, $KEY_TCOUNT TEXT, $KEY_PCOUNT TEXT)")
        database!!.execSQL(CREATE_TABLE_QUERY1)

        val CREATE_TABLE_QUERY2 = ("CREATE TABLE $TASK_TABLE ($KEY_USERID TEXT , $KEY_TASKID PRIMARY KEY, $KEY_TITLE TEXT, $KEY_COMPLETED TEXT, FOREIGN KEY($KEY_USERID) REFERENCES $USER_TABLE($KEY_ID))")
        database.execSQL(CREATE_TABLE_QUERY2)

        val CREATE_TABLE_QUERY3 = ("CREATE TABLE $POST_TABLE ($KEY_USER TEXT , $KEY_POSTID PRIMARY KEY, $KEY_PTITLE TEXT, $KEY_PBODY TEXT, FOREIGN KEY($KEY_USER) REFERENCES $USER_TABLE($KEY_ID))")
        database.execSQL(CREATE_TABLE_QUERY3)

        val CREATE_TABLE_QUERY4 = ("CREATE TABLE $COMMENT_TABLE ($KEY_POST TEXT , $KEY_COMMENTID PRIMARY KEY, $KEY_CNAME TEXT, $KEY_CEMAIL TEXT, $KEY_CBODY TEXT, FOREIGN KEY($KEY_POST) REFERENCES $POST_TABLE($KEY_POSTID))")
        database.execSQL(CREATE_TABLE_QUERY4)



    }
    // update database version
    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS $COMMENT_TABLE")
        database.execSQL("DROP TABLE IF EXISTS $POST_TABLE")
        database.execSQL("DROP TABLE IF EXISTS $TASK_TABLE")
        database.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        onCreate(database)
    }

    // list all users
    @SuppressLint("Range")
    fun listUsers() : ArrayList<User>{
        val userList = ArrayList<User>()
        val query = "SELECT * FROM $USER_TABLE ORDER BY CAST ($KEY_ID AS INTEGER)"
        val db = this.writableDatabase
        val cursor =  db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val user = User()
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)))
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)))
                user.setSurname(cursor.getString(cursor.getColumnIndex(KEY_SURNAME)))
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)))
                user.setTCount(cursor.getInt(cursor.getColumnIndex(KEY_TCOUNT)))
                user.setPCount(cursor.getInt(cursor.getColumnIndex(KEY_PCOUNT)))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }

    // list tasks
    @SuppressLint("Range")
    fun listTasks(userId : Int) : ArrayList<Tasks>{
        val taskList = ArrayList<Tasks>()
        val query = "SELECT * FROM $TASK_TABLE WHERE $KEY_USERID = $userId ORDER BY CAST ($KEY_TASKID AS INTEGER)"
        val db = this.writableDatabase
        val cursor =  db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val task = Tasks()
                task.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)))
                task.setStatus(cursor.getString(cursor.getColumnIndex(KEY_COMPLETED)))
                taskList.add(task)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return taskList
    }

    // list posts
    @SuppressLint("Range")
    fun listPosts(userId : Int) : ArrayList<Posts>{
        val postList = ArrayList<Posts>()
        val query = "SELECT * FROM $POST_TABLE WHERE $KEY_USER = $userId ORDER BY CAST ($KEY_POSTID AS INTEGER)"
        val db = this.writableDatabase
        val cursor =  db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val post = Posts()
                post.setId(cursor.getInt(cursor.getColumnIndex(KEY_POSTID)))
                post.setTitle(cursor.getString(cursor.getColumnIndex(KEY_PTITLE)))
                post.setBody(cursor.getString(cursor.getColumnIndex(KEY_PBODY)))
                postList.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return postList
    }

    // list comments
    @SuppressLint("Range")
    fun listComments(postId : Int) : ArrayList<Comments>{
        val commentList = ArrayList<Comments>()
        val query = "SELECT * FROM $COMMENT_TABLE WHERE $KEY_POST = $postId ORDER BY CAST ($KEY_COMMENTID AS INTEGER)"
        val db = this.writableDatabase
        val cursor =  db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do {
                val comment = Comments()
                comment.setName(cursor.getString(cursor.getColumnIndex(KEY_CNAME)))
                comment.setEmail(cursor.getString(cursor.getColumnIndex(KEY_CEMAIL)))
                comment.setBody(cursor.getString(cursor.getColumnIndex(KEY_CBODY)))
                commentList.add(comment)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return commentList
    }

    // add user to database
    fun addUser(user : User){
        val db= this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, user.getId().toString())
        values.put(KEY_NAME, user.getName())
        values.put(KEY_SURNAME, user.getSurname())
        values.put(KEY_EMAIL, user.getEmail())
        values.put(KEY_TCOUNT, user.getTCount().toString())
        values.put(KEY_PCOUNT, user.getPCount().toString())

        db.insert(USER_TABLE, null, values)
        db.close()
    }

    // add tasks to database
    @SuppressLint("Range")
    fun addTask(task : Tasks){
        val db= this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERID, task.getUser())
        values.put(KEY_TASKID, task.getId())
        values.put(KEY_TITLE, task.getTitle())
        values.put(KEY_COMPLETED, task.getStatus())
        db.insert(TASK_TABLE, null, values)

        // load task count
        val values1 = ContentValues()
        val query1 = "SELECT COUNT(*) AS NUBR FROM $TASK_TABLE WHERE $KEY_USERID = ${task.getUser()} ORDER BY CAST ($KEY_TASKID AS INTEGER)"
        val cursor =  db.rawQuery(query1, null)
        if(cursor.moveToFirst()){
            values1.put(KEY_TCOUNT, cursor.getInt(cursor.getColumnIndex("NUBR")))
            cursor.close()
        }
        db.update(USER_TABLE,values1, "$KEY_ID = ?", arrayOf(task.getUser().toString()))

        db.close()
    }

    // add post to database
    @SuppressLint("Range")
    fun addPost(post : Posts) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USER, post.getUser())
        values.put(KEY_POSTID, post.getId())
        values.put(KEY_PTITLE, post.getTitle())
        values.put(KEY_PBODY, post.getBody())
        db.insert(POST_TABLE, null, values)

        // load task count
        val values1 = ContentValues()
        val query1 = "SELECT COUNT(*) AS NUBR FROM $POST_TABLE WHERE $KEY_USER = ${post.getUser()} ORDER BY CAST ($KEY_POSTID AS INTEGER)"
        val cursor =  db.rawQuery(query1, null)
        if(cursor.moveToFirst()){
            values1.put(KEY_PCOUNT, cursor.getInt(cursor.getColumnIndex("NUBR")))
            cursor.close()
        }
        db.update(USER_TABLE,values1, "$KEY_ID = ?", arrayOf(post.getUser().toString()))

        db.close()
    }

    // add comment to database
    fun addComment(comment: Comments) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_POST, comment.getPost())
        values.put(KEY_COMMENTID, comment.getId())
        values.put(KEY_CNAME, comment.getName())
        values.put(KEY_CEMAIL, comment.getEmail())
        values.put(KEY_CBODY, comment.getBody())
        db.insert(COMMENT_TABLE, null, values)

        db.close()
    }
}
