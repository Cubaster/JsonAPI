package com.example.jsonapi.data

class Posts {
    private var userId : Int = 0
    private var postId : Int = 0
    private var title : String? = null
    private var body : String? = null
    private var comments : String = "Open"

    constructor()

    constructor(user: Int , id : Int, title : String, body : String){
        this.userId = user
        this.postId = id
        this.title = title
        this.body = body
    }

    fun setUser(user : Int){
        this.userId = user
    }

    fun getUser() : Int{
        return this.userId
    }

    fun setId(id : Int){
        this.postId = id
    }

    fun getId() : Int{
        return this.postId
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun getTitle() : String?{
        return this.title
    }

    fun setBody(body: String){
        this.body = body
    }

    fun getBody() : String?{
        return this.body
    }
}