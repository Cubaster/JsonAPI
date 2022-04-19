package com.example.jsonapi.data

class Tasks {
    private var userId : Int = 0
    private var taskId : Int = 0
    private var title : String? = null
    private var completed : String? = null

    constructor()

    constructor(user: Int , id : Int, title : String, completed : String){
        this.userId = user
        this.taskId = id
        this.title = title
        this.completed = completed
    }

    fun setUser(user : Int){
        this.userId = user
    }

    fun getUser() : Int{
        return this.userId
    }

    fun setId(id : Int){
        this.taskId = id
    }

    fun getId() : Int{
        return this.taskId
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun getTitle() : String?{
        return this.title
    }

    fun setStatus(completed: String){
        this.completed = completed
    }

    fun getStatus() : String?{
        return this.completed
    }
}