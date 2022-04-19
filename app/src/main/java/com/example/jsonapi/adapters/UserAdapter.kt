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
import com.example.jsonapi.PostsAndTasks
import com.example.jsonapi.R
import com.example.jsonapi.data.User


class UserAdapter (private val context: Context, private val arrayList: java.util.ArrayList<User>) : BaseAdapter() {
    private lateinit var name: TextView
    private lateinit var surname: TextView
    private lateinit var email: TextView
    private lateinit var taskcount: TextView
    private lateinit var postcount: TextView
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
        convertView = LayoutInflater.from(context).inflate(R.layout.user_list, parent, false)
        linearLayout = convertView.findViewById(R.id.userList)
        name = convertView.findViewById(R.id.name)
        surname = convertView.findViewById(R.id.surname)
        email = convertView.findViewById(R.id.email)
        taskcount = convertView.findViewById(R.id.taskcount)
        postcount = convertView.findViewById(R.id.postcount)

        linearLayout.setOnClickListener(){
            val i = Intent(context, PostsAndTasks::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val b = Bundle()
            b.putInt("id", arrayList[position].getId())
            b.putString("name", arrayList[position].getName())
            i.putExtras(b) //Put your id to your next Intent
            startActivity(context, i, b)
        }

        name.text = arrayList[position].getName()
        surname.text = arrayList[position].getSurname()
        email.text = arrayList[position].getEmail()
        taskcount.text = arrayList[position].getTCount().toString()
        postcount.text = arrayList[position].getPCount().toString()
        return convertView
    }
}
