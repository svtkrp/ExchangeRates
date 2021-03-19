package com.svtkrp.exchangerates

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.svtkrp.exchangerates.ui.SectionsPagerAdapter

class MainActivity : AppCompatActivity(), Observer<List<Rate>> {

    lateinit var ratesViewModel: RatesViewModel

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ratesViewModel = ViewModelProvider(this).get(RatesViewModel::class.java)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onStart() {
        super.onStart()
        ratesViewModel.ratesLiveData.observe(this, this)
    }

    override fun onChanged(rates: List<Rate>?) {
        updateRates(rates!!)
    }

    private fun updateRates(rates: List<Rate>) {
        sectionsPagerAdapter.updateData(rates)
    }
}