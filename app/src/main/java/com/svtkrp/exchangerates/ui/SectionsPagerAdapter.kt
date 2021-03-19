package com.svtkrp.exchangerates.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.ui.convert.ConvertFragment
import com.svtkrp.exchangerates.ui.rates.RatesFragment
import java.lang.IllegalArgumentException

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RatesFragment.newInstance()
            1 -> ConvertFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}