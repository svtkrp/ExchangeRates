package com.svtkrp.exchangerates.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.svtkrp.exchangerates.Rate
import com.svtkrp.exchangerates.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import okhttp3.MediaType.Companion.toMediaType

class RatesRepo {
    companion object {
        private val scope = CoroutineScope(Dispatchers.IO)

        fun listRates(): LiveData<List<Rate>> {
            val ratesLiveData = MutableLiveData<List<Rate>>()

            scope.launch() {
                val map: Map<String, Rate> = RetrofitModule.ratesApi.getResponse().Valute
                val list: MutableList<Rate> = mutableListOf()
                for (item in map) {
                    list.add(item.value)
                }
                ratesLiveData.postValue(list)
            }
            return ratesLiveData
        }
    }

    private interface RatesApi {
        @GET("daily_json.js")
        suspend fun getResponse(): Response
    }

    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val ratesApi: RatesApi = retrofit.create()
    }

}


