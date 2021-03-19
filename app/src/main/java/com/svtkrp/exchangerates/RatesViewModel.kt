package com.svtkrp.exchangerates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.svtkrp.exchangerates.ui.main.RatesRepo

class RatesViewModel : ViewModel() {
    var ratesLiveData: LiveData<List<Rate>>
    init {
        ratesLiveData = loadRates()
    }

    private fun loadRates(): LiveData<List<Rate>> {
        return RatesRepo.listRates()
    }
}