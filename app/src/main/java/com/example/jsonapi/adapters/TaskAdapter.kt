package com.example.jsonapi.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.jsonapi.R
import com.example.jsonapi.data.Tasks

class TaskAdapter (private val context: Context, private val arrayList: java.util.ArrayList<Tasks>) : BaseAdapter() {
    private lateinit var title: TextView
    private lateinit var completed: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.task_list, parent, false)
        title = convertView.findViewById(R.id.name)
        completed = convertView.findViewById(R.id.completed)

        title.text = arrayList[position].getTitle()
        completed.text = if (arrayList[position].getStatus().toBoolean()) "Yes" else "No"
        return convertView
    }
}