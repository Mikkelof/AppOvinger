package com.example.oving5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.URLEncoder

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {
    private lateinit var name_input: EditText
    private lateinit var card_number_input: EditText
    private lateinit var confirm_button: Button
    private lateinit var send_button: Button
    private lateinit var number: EditText
    private lateinit var sessionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name_input = findViewById(R.id.name_input)
        card_number_input = findViewById(R.id.card_number_input)
        number = findViewById(R.id.number_input)
        confirm_button = findViewById(R.id.confirm_button)
        send_button = findViewById(R.id.send_button)

        confirm_button.isEnabled = false
        send_button.isEnabled = false

        inputFieldListeners()
        sendButtonListener()
        confirmButtonListener()
    }

    private fun inputFieldListeners() {
        var nameAdded = false
        name_input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Leaving empty as it is not needed but had to generate it
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Leaving empty as it is not needed but had to generate it
            }

            override fun afterTextChanged(p0: Editable?) {
                if(name_input.text.toString() != "") {
                    nameAdded = true
                }
            }
        })

        card_number_input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Leaving empty as it is not needed but had to generate it
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Leaving empty as it is not needed but had to generate it
            }

            override fun afterTextChanged(p0: Editable?) {
                if(nameAdded && card_number_input.text.toString() != "") {
                    confirm_button.isEnabled = true
                }
            }
        })
    }

    private fun confirmButtonListener() {
        confirm_button.setOnClickListener {
            val name = name_input.text.toString()
            val card_number = card_number_input.text.toString()

            val nameEncoded = URLEncoder.encode(name, "UTF-8")
            performRequest(nameEncoded, card_number)
            send_button.isEnabled = true
        }
    }

    private fun sendButtonListener() {
        send_button.setOnClickListener {
            val selected_number = number.text.toString().toInt()

            performRequest(selected_number)
        }
    }

    private fun performRequest(name: String, cardNumber: String) {

        val cookie = (1..10000).random()

        CoroutineScope(Dispatchers.IO).launch {
            val network = HttpWrapper("$URL?navn=$name&kortnummer=$cardNumber")
            val response: String = network.post("$URL?navn=$name&kortnummer=$cardNumber", cookie)
            sessionId = network.getSessionId()

            MainScope().launch {
                setResult(response.dropLast(5))
            }
        }
    }

    private fun performRequest(number: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val network = HttpWrapper("$URL?tall=$number")
            val response: String = network.post("$URL?tall=$number", sessionId)

            MainScope().launch {
                setResult(response.dropLast(5))
            }
        }
    }

    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.result).text = response
    }
}