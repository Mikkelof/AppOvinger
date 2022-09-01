package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

class RandomNumberActivity : Activity() {
    var upperLimit : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.random_number_activity)
        upperLimit = intent.getIntExtra("upperLimit", upperLimit)
        randomNum(upperLimit)
    }

    private fun randomNum(upperLimit: Int) {
        val num: Int = (0..upperLimit).random()
        setResult(RESULT_OK, Intent().putExtra("num", num))
        //Toast.makeText(this, num.toString(), Toast.LENGTH_LONG).show()
        finish()
    }
}