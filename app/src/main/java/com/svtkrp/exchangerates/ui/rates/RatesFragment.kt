package com.svtkrp.exchangerates.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate
import com.svtkrp.exchangerates.RatesViewModel
import com.svtkrp.exchangerates.ui.SectionsPagerAdapter
import kotlinx.coroutines.*

class RatesFragment : Fragment(), Observer<List<Rate>> {

    lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    private lateinit var root: View
    private lateinit var adapter: RatesAdapter
    private lateinit var refreshLayout: SwipeRefreshLayout

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_rates, container, false)
        adapter = RatesAdapter(context, emptyList())
        val recyclerView: RecyclerView = root.findViewById(R.id.recycle_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.divider, null)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.setHasFixedSize(true)

        refreshLayout = root.findViewById(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            loadRates()
        })

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun loadRates() {
        ViewModelProvider(this).get(RatesViewModel::class.java)
            .ratesLiveData.observe(this, this)
        scope.launch {
            delay(1000)
            refreshLayout.setRefreshing(false)
        }
    }

    override fun onChanged(rates: List<Rate>?) {
        updateRates(rates!!)
    }

    private fun updateRates(rates: List<Rate>) {
        sectionsPagerAdapter.updateData(rates)
    }

    fun updateData(rates: List<Rate>) {
        adapter.rates = rates
        adapter.notifyDataSetChanged()

        Snackbar.make(root, R.string.refreshed, Snackbar.LENGTH_SHORT)
            .setAction("Action", null).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(adapter: SectionsPagerAdapter): RatesFragment {
            val fragment: RatesFragment = RatesFragment()
            fragment.sectionsPagerAdapter = adapter
            return fragment
        }
    }
}