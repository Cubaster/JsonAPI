package com.example.jsonapi.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.jsonapi.CommentSection
import com.example.jsonapi.R
import com.example.jsonapi.data.Posts

class PostAdapter (private val context: Context, private val arrayList: java.util.ArrayList<Posts>) : BaseAdapter() {
    private lateinit var title: TextView
    private lateinit var body: TextView
    private lateinit var linearLayout: LinearLayout
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
        convertView = LayoutInflater.from(context).inflate(R.layout.post_list, parent, false)
        linearLayout = convertView.findViewById(R.id.postList)
        title = convertView.findViewById(R.id.name)
        body = convertView.findViewById(R.id.body)

        linearLayout.setOnClickListener(){
            val i = Intent(context, CommentSection::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val b = Bundle()
            b.putInt("id", arrayList[position].getId())
            b.putString("title", arrayList[position].getTitle())
            b.putString("body", arrayList[position].getBody())
            i.putExtras(b)
            startActivity(context, i, b)
        }

        title.text = arrayList[position].getTitle()
        body.text = arrayList[position].getBody()
        return convertView
    }
}
