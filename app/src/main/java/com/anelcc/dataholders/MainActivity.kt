package com.anelcc.dataholders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.anelcc.dataholders.databinding.ActivityMainBinding
import com.anelcc.dataholders.presentation.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        binding.stateFlow.setOnClickListener {
           viewModel.triggerStateFlow()
        }
        binding.flow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collectLatest {
                    binding.flowTitle.text = it
                }
            }
        }
        
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(this) {
            binding.livedatatitle.text = it
        }
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                binding.stateFlowTitle.text = it
            }
        }
    }
}