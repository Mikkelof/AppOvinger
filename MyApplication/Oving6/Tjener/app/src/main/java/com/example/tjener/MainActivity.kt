package com.example.tjener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val PORT: Int = 12345
    private var connections = ArrayList<Socket>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
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
        set(str) {
            MainScope().launch {
                val message: String = textView.getText().toString()
                textView.text = message + "\n" + str
            }
            field = str
        }

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                ui = "Starter Tjener ..."
                // "innapropriate blocking method call" advarsel betyr at tråden
                // stopper helt opp og ikke går til neste linje før denne fullfører, i dette
                // eksempelet er ikke dette så farlig så vi ignorerer advarselen.
                ServerSocket(PORT).use { serverSocket: ServerSocket ->

                    ui = "ServerSocket opprettet, venter på at en klient kobler seg til...."

                    while (true) {
                        val clientSocket = serverSocket.accept()
                        ui = "Klient koblet til: $clientSocket"
                        clientHandler(clientSocket)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

    private suspend fun clientHandler(clientSocket: Socket) = coroutineScope {
            CoroutineScope(Dispatchers.IO).launch {
                connections.add(clientSocket)
                sendToClient(clientSocket, "Velkommen")
                readFromClient(clientSocket)
            }
        }

    private suspend fun readFromClient(socket: Socket) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                val message = reader.readLine()

                if (message == null) {
                    ui = "Klient avkoblet"
                    connections.remove(socket)
                    socket.close()
                    break
                } else {
                    connections.filter { it !== socket }.forEach { sendToClient(it, message) }
                    println("Klienten sier:\n$message")
                }
            }
        }
    }

    private suspend fun sendToClient(socket: Socket, message: String) = coroutineScope {
        CoroutineScope(Dispatchers.IO).launch {
            val writer = PrintWriter(socket.getOutputStream(), true)
            writer.println(message)
            println("Sendte følgende til klienten:\n$message")
        }
    }
}