package com.anelcc.dataholders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    //Live data contain data with even rotate to phone
    //This is deprecating
    private val _liveData = MutableLiveData("Hello World!")
    val liveData: LiveData<String> = _liveData

    //State flow use to store state and the flow benefit to update the UI
    //this is part of the coroutine framework to collect data.
    //Flows can be a hot flow (they will collecting values)
    // and cold flow will if there is not collector
    private val _stateFlow = MutableStateFlow("Hello World!")
    val stateFlow: MutableStateFlow<String> = _stateFlow


    fun triggerLiveData() {
        _liveData.value = "Live Data Triggered!"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "State Flow Triggered!"
    }

    //regular flow will no save the state and it will refreshed if we rotate the phone
    //this will no be initialized until we collect the data
    fun triggerFlow(): Flow<String> {
        return flow { // Use the 'flow' builder
            repeat(5) {
                // Now 'emit' and 'delay' are in the correct context
                emit("Item ${it+1}")
                delay(1000L)
            }
        }
    }

}