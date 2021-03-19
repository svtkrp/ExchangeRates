package com.svtkrp.exchangerates.ui.convert

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate

class ConvertAdapter(private val myContext: Context, var rates: List<Rate>)
    : ArrayAdapter<Rate>(myContext, R.layout.spinner_item, rates) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        getNewView(position, convertView)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        getNewView(position, convertView)

    fun getNewView(position: Int, convertView: View?): View {
        val rate = getItem(position)
        var newView = convertView

        if (newView == null) {
            newView = LayoutInflater.from(myContext)
                .inflate(R.layout.spinner_item, null)
        }
        (newView!!.findViewById(R.id.text1) as TextView).text = rate!!.name
        return newView
    }
}