package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity() {
    private val upperLimit : Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent(".RandomNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivity(intent)
    }
}