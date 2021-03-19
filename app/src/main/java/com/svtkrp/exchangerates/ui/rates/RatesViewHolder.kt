package com.svtkrp.exchangerates.ui.rates

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate

class RatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.rate_name)
    private val value: TextView = itemView.findViewById(R.id.rate_value)

    fun bind(rate: Rate) {
        name.text = rate.name
        value.text = rate.value.toString()
    }

}