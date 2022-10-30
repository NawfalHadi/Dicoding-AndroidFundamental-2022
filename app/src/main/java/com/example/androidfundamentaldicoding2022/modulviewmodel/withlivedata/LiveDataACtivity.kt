package com.example.androidfundamentaldicoding2022.modulviewmodel.withlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.androidfundamentaldicoding2022.R
import com.example.androidfundamentaldicoding2022.databinding.ActivityLiveDataBinding
import com.example.androidfundamentaldicoding2022.modulviewmodel.MainViewModel

class LiveDataACtivity : AppCompatActivity() {

    private lateinit var binding : ActivityLiveDataBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subcribe()
    }

    private fun subcribe() {
        viewModel.getElapsedTime().observe(this) {
            val newText = this.resources.getString(R.string.seconds, it)
            binding.timerTextview.text = newText
        }
    }
}