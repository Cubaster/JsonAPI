package com.example.jsonapi.data

class Comments {
    private var postId : Int = 0
    private var commentId : Int = 0
    private var name : String? = null
    private var email : String? = null
    private var body : String? = null

    constructor()

    constructor(post: Int , id : Int, name : String, email : String, body : String){
        this.postId = post
        this.commentId = id
        this.name = name
        this.email = email
        this.body = body
    }

    fun setPost(post : Int){
        this.postId = post
    }

    fun getPost() : Int{
        return this.postId
    }

    fun setId(id : Int){
        this.commentId = id
    }

    fun getId() : Int{
        return this.commentId
    }

    fun setName(name: String){
        this.name = name
    }

    fun getName() : String?{
        return this.name
    }

    fun setEmail(email: String){
        this.email = email
    }

    fun getEmail() : String?{
        return this.email
    }

    fun setBody(body: String){
        this.body = body
    }

    fun getBody() : String?{
        return this.body
    }
}