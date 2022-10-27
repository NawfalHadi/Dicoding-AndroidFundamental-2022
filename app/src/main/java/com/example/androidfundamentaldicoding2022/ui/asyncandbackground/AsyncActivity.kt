package com.example.androidfundamentaldicoding2022.ui.asyncandbackground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.example.androidfundamentaldicoding2022.R
import com.example.androidfundamentaldicoding2022.databinding.ActivityAsyncBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class AsyncActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsyncBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStart.setOnClickListener {

            // Not Using Async
//            try {
//                for (i in 0..10){
//                    Thread.sleep(500)
//                    val percantage = i * 10
//                    if (percantage == 100) {
//                        binding.tvStatus.text = getString(R.string.task_completed)
//                    } else {
//                        binding.tvStatus.text = String.format(getString(R.string.compressing), percantage)
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//              Menggunakan exceutor dan handler
//            useAsync()
            useCoroutine()
        }
    }

    private fun useCoroutine() {
        lifecycleScope.launch(Dispatchers.Default) {
            for (i in 0..10) {
                delay(500)
                val percantage = i * 10
                withContext(Dispatchers.Main) {
                    binding.btnStart.isClickable = false
                    if (percantage == 100) {
                        binding.tvStatus.text = getString(R.string.task_completed)
                        binding.btnStart.isClickable = true
                    } else {
                        binding.tvStatus.text = String.format(getString(R.string.compressing), percantage)
                    }
                }
            }
        }
    }

    private fun useAsync() {

        // Kekurangan diimplementasikan terlebih dahulu keknya
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            try {
                // Run the loop in background thread
                for (i in 0..10) {
                    Thread.sleep(500)
                    val percantage = i * 10

                    // using handler for set the ui
                    handler.post {
                        if (percantage == 100) {
                            binding.tvStatus.text = getString(R.string.task_completed)
                        } else {
                            binding.tvStatus.text = String.format(getString(R.string.compressing), percantage)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}