package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val upperLimit: Int = 100
    private var num: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent(".RandomNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, num)
    }

    fun onClickStartMathActivity(v: View?) {
        val intent = Intent(".MathActivity")
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (data != null) {
            num = data.getIntExtra("num", num)
            val textView: TextView = findViewById(R.id.textView) as TextView
            textView.text = num.toString()
        }
    }
}