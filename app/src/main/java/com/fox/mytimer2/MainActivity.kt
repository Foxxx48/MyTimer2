package com.fox.mytimer2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fox.mytimer2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding?: throw RuntimeException("ActivityMainBinding = null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.runTimer()
        observeViewModels()

        binding.btnStart.setOnClickListener {
            viewModel.isRunning = true




        }

        binding.btnStop.setOnClickListener {
            viewModel.isRunning = false



        }

        binding.btnClear.setOnClickListener {
            viewModel.isRunning = false
            viewModel.sec = 0
        }


    }

    private fun observeViewModels() {
        viewModel.time.observe(this) {
            binding.tvTime.text = it
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}