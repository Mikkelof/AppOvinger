package com.example.oving3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        initSave(position)
    }

    fun initSave(position: Int?) {
        val button: Button = findViewById(R.id.confirmChanges)
        button.setOnClickListener {
            val nameTextView: EditText = findViewById(R.id.editTextName)
            val name: String = nameTextView.text.toString()
            val dateTextView: EditText = findViewById(R.id.editTextBirthday)
            val date: String = dateTextView.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("date", date)
            resultIntent.putExtra("position", position.toString())
            setResult(1, resultIntent)
            finish()
        }
    }
}