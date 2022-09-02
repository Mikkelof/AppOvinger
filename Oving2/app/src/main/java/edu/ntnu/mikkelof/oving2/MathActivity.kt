package edu.ntnu.mikkelof.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MathActivity : Activity()  {
    private var num: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)
    }

    fun onClickAdd(v: View?) {
        val upperLimitTextView: TextView = findViewById(R.id.upper_limit)
        val upperLimit: String = upperLimitTextView.text.toString()

        val num1TextView: TextView = findViewById(R.id.num1)
        val num1: String = num1TextView.text.toString()

        val num2TextView: TextView = findViewById(R.id.num2)
        val num2: String = num2TextView.text.toString()

        val answerTextView: TextView = findViewById(R.id.answer)
        val answer: String = answerTextView.text.toString()

        val corAns = num1.toInt() + num2.toInt()
        if (corAns == answer.toInt()) {
            Toast.makeText(this, "Riktig!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Feil, riktig svar er $corAns", Toast.LENGTH_LONG).show()
        }
        getRandomNums(upperLimit.toInt())
    }

    fun onClickMultiply(v: View?) {
        val upperLimitTextView: TextView = findViewById(R.id.upper_limit)
        val upperLimit: String = upperLimitTextView.text.toString()

        val num1TextView: TextView = findViewById(R.id.num1)
        val num1: String = num1TextView.text.toString()

        val num2TextView: TextView = findViewById(R.id.num2)
        val num2: String = num2TextView.text.toString()

        val answerTextView: TextView = findViewById(R.id.answer)
        val answer: String = answerTextView.text.toString()

        val corAns = num1.toInt() + num2.toInt()
        if (corAns == answer.toInt()) {
            Toast.makeText(this, "Riktig!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Feil, riktig svar er $answer", Toast.LENGTH_LONG).show()
        }
        getRandomNums(upperLimit.toInt())
    }

    fun getRandomNums(upperLimit: Int) {
        val intent = Intent(".RandomNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, num)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (data != null) {
            var num1 = data.getIntExtra("num", num) //Vet at dette er de samme to tallene, men fikk ikke
            var num2 = data.getIntExtra("num", num) //til å generere to forskjellige tall
            val textViewNum1: TextView = findViewById(R.id.num1)
            textViewNum1.text = num1.toString()
            val textViewNum2: TextView = findViewById(R.id.num2)
            textViewNum2.text = num2.toString()
        }
    }
}