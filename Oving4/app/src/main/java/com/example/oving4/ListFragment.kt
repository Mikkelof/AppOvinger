package com.example.oving4

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class InfoFragment : ListFragment() {
    private var movies: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movies = resources.getStringArray(R.array.movies)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, movies)
        }
    }
}