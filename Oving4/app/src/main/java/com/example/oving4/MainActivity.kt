package com.example.oving4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), MovieListFragment.OnFragmentInteractionListener
{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onFragmentInteraction(index: Int?) {
        val infoFragment = supportFragmentManager.findFragmentById(R.id.infoFragment) as InfoFragment
        infoFragment.setText(index)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_previous -> onClickPreviousButton()
            R.id.menu_next -> onClickNextButton()
            else -> return false
        }
        return true
    }

    private fun onClickPreviousButton(){
        val infoFragment = supportFragmentManager.findFragmentById(R.id.infoFragment) as InfoFragment
        infoFragment.onClickPrevious()
    }
    private fun onClickNextButton(){
        val infoFragment = supportFragmentManager.findFragmentById(R.id.infoFragment) as InfoFragment
        infoFragment.onClickNext()
    }
}