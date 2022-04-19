package com.example.jsonapi.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.jsonapi.R
import com.example.jsonapi.data.Comments

class CommentAdapter (private val context: Context, private val arrayList: java.util.ArrayList<Comments>) : BaseAdapter() {
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var body: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.comment_list, parent, false)
        name = convertView.findViewById(R.id.name)
        email = convertView.findViewById(R.id.email)
        body = convertView.findViewById(R.id.body)

        name.text = arrayList[position].getName()
        email.text = arrayList[position].getEmail()
        body.text = arrayList[position].getBody()
        return convertView
    }
}