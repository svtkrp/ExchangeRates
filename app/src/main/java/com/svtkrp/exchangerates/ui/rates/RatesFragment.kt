package com.svtkrp.exchangerates.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.snackbar.Snackbar
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate
import com.svtkrp.exchangerates.ui.main.rates
import com.svtkrp.exchangerates.ui.main.rates2

class RatesFragment : Fragment() {

    private lateinit var root: View
    private lateinit var adapter: RatesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_rates, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycle_view)
        adapter = RatesAdapter(context, rates)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.divider, null)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.setHasFixedSize(true)

        val refreshLayout: SwipeRefreshLayout = root.findViewById(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener(OnRefreshListener {
            onRatesChanged(rates2)
            refreshLayout.setRefreshing(false)
        })

        return root
    }

    fun onRatesChanged(rates: List<Rate>) {
        adapter.rates = rates
        adapter.notifyDataSetChanged()

        Snackbar.make(root, R.string.refreshed, Snackbar.LENGTH_SHORT)
            .setAction("Action", null).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(): RatesFragment = RatesFragment()
    }
}