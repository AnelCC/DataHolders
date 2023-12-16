package com.anelcc.dataholders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.anelcc.dataholders.databinding.ActivityMainBinding
import com.anelcc.dataholders.presentation.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.livedata.setOnClickListener {
           viewModel.triggerLiveData()
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(this) {
            binding.livedatatitle.text = it
        }
    }
}