package com.anelcc.dataholders

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

//    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
    }
}