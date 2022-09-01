package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent(".RandomNumberActivity")
        startActivity(intent)
    }
}