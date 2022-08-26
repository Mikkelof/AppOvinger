package com.example.oving1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add("Mikkel")
        menu.add("Ofrim")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals("Mikkel")) {
            Log.w("Navn","Mikkel")
        }
        if (item.title.equals("Ofrim")) {
            Log.e("Navn", "Ofrim")
        }
        return true
    }
}