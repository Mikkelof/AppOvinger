package com.example.oving3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable

class MainActivity : Activity() {
    private var friends: ArrayList<Friend> = ArrayList<Friend>()
    private lateinit var listView: ListView
    private lateinit var editTextName: EditText
    private lateinit var editTextDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        friends.add(Friend("Mikkel", "17. januar 1999"))
        friends.add(Friend("Michael", "17. januar 1988"))
        friends.add(Friend("Miguel", "17. januar 1977"))

        listView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(".EditFriendActivity")
            intent.putExtra("friend", friends.get(position) as Serializable)
            intent.putExtra("position", position.toString())
            startActivityForResult(intent, 1)
        }
    }

    fun onClickAddFriend(v: View?) {
        editTextName = findViewById(R.id.editTextName)
        editTextDate = findViewById(R.id.editTextBirthday)

        friends.add(Friend(editTextName.getText().toString(), editTextDate.getText().toString()))
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        listView.adapter = adapter
    }
}