package com.svtkrp.exchangerates.ui.rates

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate

class RatesAdapter(
    context: Context?,
    var rates: List<Rate>
) : RecyclerView.Adapter<RatesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(inflater.inflate(R.layout.rates_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = rates.size

    fun getItem(position: Int): Rate = rates[position]

}