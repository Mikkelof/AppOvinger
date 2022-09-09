package com.example.oving3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private var friends: ArrayList<Friend> = ArrayList<Friend>()
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        friends.add(Friend("Mikkel", "17. januar 1999"))
        friends.add(Friend("Michael", "17. januar 1988"))
        friends.add(Friend("Miguel", "17. januar 1977"))

        listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        listView.adapter = adapter
    }
}