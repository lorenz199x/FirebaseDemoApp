package com.example.demoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HeroAdapter(val mCtx: Context, val layoutResId: Int, val heroList: List<Hero>)
// inherit the class
    :ArrayAdapter<Hero>(mCtx, layoutResId, heroList) {
    /*
    * overide a method
    * go to generate
    * select overide members
    * select getView
    * */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(layoutResId, null)

        // get textView
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        //get hero name
        val hero = heroList[position]
        textViewName.text = hero.name

        return view;
    }
}