package com.anelcc.dataholders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    //Live data contain data with even rotate to phone
    private val _liveData = MutableLiveData("Hello World!")
    val liveData: LiveData<String> = _liveData

    //State flow use to store state and the flow benefit to update the UI
    //this is part of the curutine framework to collect data.
    private val _stateFlow = MutableStateFlow("Hello World!")
    val stateFlow: MutableStateFlow<String> = _stateFlow


    fun triggerLiveData() {
        _liveData.value = "Live Data Triggered!"
    }

    fun triggerStateFlow() {
        _liveData.value = "State Flow Triggered!"
    }
}