package com.example.androidfundamentaldicoding2022.modulviewmodel

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamentaldicoding2022.databinding.ActivityViewModelBinding
import com.example.androidfundamentaldicoding2022.modulviewmodel.withlivedata.LiveDataACtivity

class ViewModelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewModelBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnUseLivedata.setOnClickListener {
            startActivity(Intent(this, LiveDataACtivity::class.java))
        }

        displayResult()
        binding.btnCalculate.setOnClickListener {
            submitValue()
        }
    }

    private fun submitValue(){
        val edtHeight = binding.edtHeight.text.toString()
        val edtLength = binding.edtLength.text.toString()
        val edtWidth = binding.edtWidth.text.toString()

        when {
            edtHeight.isEmpty() -> binding.edtHeight.error = "Fill the height Field"
            edtLength.isEmpty() -> binding.edtLength.error = "Fill the length field"
            edtWidth.isEmpty() -> binding.edtWidth.error = "Fill the width field"
            else -> {
                viewModel.calculate(edtWidth, edtHeight, edtLength)
                displayResult()
            }
        }
    }


    private fun displayResult() {
        binding.tvResult.text = viewModel.result.toString()
    }
}