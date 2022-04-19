package com.example.jsonapi.data

class User {
    private var id : Int = 0
    private var name : String? = null
    private var surname : String? = null
    private var email : String? = null
    private var taskcount : Int = 0
    private var postcount : Int = 0

    constructor()

    constructor(id : Int, name: String, surname : String, email : String){
        this.id  = id
        this.name = name
        this.surname = surname
        this.email = email
    }

    fun getId() : Int{
        return this.id
    }

    fun getName() : String?{
        return this.name
    }

    fun getSurname() : String?{
        return this.surname
    }

    fun getEmail() : String?{
        return this.email
    }

    fun getTCount() : Int{
        return this.taskcount
    }

    fun getPCount() : Int{
        return this.postcount
    }

    fun setId(id : Int){
        this.id = id
    }

    fun setName(name : String){
        this.name = name
    }

    fun setSurname(surname : String){
        this.surname = surname
    }

    fun setEmail(email: String){
        this.email = email
    }

    fun setTCount(count : Int){
        this.taskcount = count
    }

    fun setPCount(count : Int){
        this.postcount = count
    }
}