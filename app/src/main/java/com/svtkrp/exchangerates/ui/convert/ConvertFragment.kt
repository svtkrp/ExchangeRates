package com.svtkrp.exchangerates.ui.convert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate
import com.svtkrp.exchangerates.ui.main.rates


class ConvertFragment : Fragment() {

    private lateinit var inputNumber: EditText
    private lateinit var convertedNumber: TextView
    private var selected: Double = 100.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_convert, container, false)
        inputNumber = root.findViewById(R.id.input_number)
        convertedNumber = root.findViewById(R.id.converted_number)

        val spinner: Spinner = root.findViewById(R.id.currency_spinner)
        val spinnerAdapter = ConvertAdapter(context!!, rates)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View,
                position: Int, id: Long) {
                selected = (spinner.selectedItem as Rate).value
                convert()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { convert() }
        })

        return root
    }

    fun convert() {
        val input = inputNumber.text.toString()
        convertedNumber.text = if (input == "") getString(R.string.converted_default)
        else (input.toDouble() * selected).toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(): ConvertFragment = ConvertFragment()
    }
}