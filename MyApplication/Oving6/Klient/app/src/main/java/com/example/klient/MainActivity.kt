package com.example.klient

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private val SERVER_IP: String = "10.0.2.2"
    private val SERVER_PORT: Int = 12345
    var received = ArrayList<String>()
    var sent = ArrayList<String>()
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    var server: Socket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        start()
    }

    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulatoren med
     *
     * ```
     * ui = "noe"
     * ```
     */
    private var ui: String? = ""
        @SuppressLint("SetTextI18n")
        set(str) {
            MainScope().launch {
                val message: String = textView.getText().toString()
                textView.text = message + "\n" + str
            }
            field = str
        }


    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Kobler til tjener..."
            try {
                server = Socket(SERVER_IP, SERVER_PORT)
                ui = "Koblet til tjener:\n$server"
                startListeningToServer()

            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

    private fun startListeningToServer() {
        CoroutineScope(Dispatchers.IO).launch {
            server?.let {
                while (true) {
                    val reader = BufferedReader(InputStreamReader(it.getInputStream()))
                    val message = reader.readLine()
                    if (message !== null) {
                        ui = message
                        received.add(message)
                    } else {
                        it.close()
                        break
                    }
                }
            }
        }
    }

    fun onClickSendMessage(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            var message = editText.text.toString()
            sendToServer(message)
        }
    }

    private fun readFromServer(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        ui = "Melding fra tjeneren:\n$message"
    }

    private suspend fun sendToServer(message: String) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            server?.let {
                val writer = PrintWriter(it.getOutputStream(), true)
                writer.println(message)
                sent.add(message)
                ui = "Du: $message"
            }
        }
    }
}