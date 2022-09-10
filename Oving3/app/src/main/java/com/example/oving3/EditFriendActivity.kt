package com.example.oving3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import java.io.Serializable

class EditFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_friend)

        val friend: Friend = intent.getSerializableExtra("friend") as Friend
        val position: Int? = intent.getStringExtra("position")?.toIntOrNull()

        val nameEditText: EditText = findViewById(R.id.editTextName)
        val dateEditText: EditText = findViewById(R.id.editTextBirthday)

        nameEditText.setText(friend.name)
        dateEditText.setText(friend.dateOfBirth)

    }
}