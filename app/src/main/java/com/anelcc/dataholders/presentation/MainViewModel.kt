package com.anelcc.dataholders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>("Hello World!")
    val liveData: LiveData<String> = _liveData


    fun triggerLiveData() {
        _liveData.value = "New Value"
    }
}