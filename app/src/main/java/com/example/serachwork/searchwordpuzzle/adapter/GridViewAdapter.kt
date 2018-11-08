package com.example.serachwork.searchwordpuzzle.adapter

import android.content.Context
import android.support.v7.widget.GridLayout
import android.view.View
import android.view.ViewGroup
import android.widget.*


class GridViewAdapter : BaseAdapter {

    var mcontext : Context? = null
    var wordList : CharArray? = null

    constructor(mcontext: Context?, wordList: CharArray) : super() {
        this.mcontext = mcontext
        this.wordList = wordList
    }


    override fun getCount(): Int {
        return wordList!!.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var textView : TextView? = null
        var gridView = GridView(mcontext)
        if (convertView == null) {
            textView = TextView(mcontext)
            textView.setTextSize(20f)
            textView.text = wordList!!.get(position).toString()
        } else {
            textView = convertView as TextView?
        }


        //imageView.setImageResource(R.drawable.chai)
        return textView!!
    }
}