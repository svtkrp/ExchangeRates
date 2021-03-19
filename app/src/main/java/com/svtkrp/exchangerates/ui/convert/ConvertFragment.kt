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
import com.google.android.material.snackbar.Snackbar
import com.svtkrp.exchangerates.R
import com.svtkrp.exchangerates.Rate


class ConvertFragment : Fragment() {

    private lateinit var root: View
    private lateinit var inputNumber: EditText
    private lateinit var convertedNumber: TextView
    private lateinit var spinner: Spinner
    private var selected: Double = 100.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_convert, container, false)
        inputNumber = root.findViewById(R.id.input_number)
        convertedNumber = root.findViewById(R.id.converted_number)

        spinner = root.findViewById(R.id.currency_spinner)
        spinner.adapter = ConvertAdapter(context!!, emptyList())

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
        else (input.toDouble() / selected).toString()
    }

    fun updateData(rates: List<Rate>) {
        spinner.adapter = ConvertAdapter(context!!, rates)

        Snackbar.make(root, R.string.refreshed, Snackbar.LENGTH_SHORT)
            .setAction("Action", null).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(): ConvertFragment = ConvertFragment()
    }
}