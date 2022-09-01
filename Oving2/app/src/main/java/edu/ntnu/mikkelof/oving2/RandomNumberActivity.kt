package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class RandomNumberActivity : Activity() {
    var upperLimit : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.random_number_activity)
        upperLimit = intent.getIntExtra("upperLimit", upperLimit)
        Toast.makeText(this, randomNum().toString(), Toast.LENGTH_LONG).show()
    }

    fun randomNum(): Int {
        return (0..upperLimit).random()
    }
}