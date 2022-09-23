package com.example.oving4

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {
    private var descriptions: Array<String> = arrayOf()
    private var images: TypedArray? = null
    private var imageIDs: IntArray = intArrayOf()
    private var indexDisplayed: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        descriptions  = resources.getStringArray(R.array.moviedescriptions)
        images = resources.obtainTypedArray(R.array.images)
        imageIDs = IntArray(images!!.length())
        for (i in 0 until images!!.length()) imageIDs[i] = images!!.peekValue(i).resourceId
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    fun setText(index: Int?) {
        requireView().findViewById<TextView>(R.id.movieDescriptionTextView).text = descriptions[index!!]
        requireView().findViewById<ImageView>(R.id.movieImageImageView).setImageResource(imageIDs[index])
        indexDisplayed =  index
    }

    fun onClickPrevious(){
        if(indexDisplayed == 0) indexDisplayed = imageIDs.size-1 else indexDisplayed -=1
        setText(indexDisplayed)
    }

    fun onClickNext(){
        if(indexDisplayed == imageIDs.size-1) indexDisplayed = 0 else indexDisplayed +=1
        setText(indexDisplayed)
    }

}