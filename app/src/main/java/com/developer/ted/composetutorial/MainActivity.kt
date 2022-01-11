package com.developer.ted.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developer.ted.composetutorial.compose.MessageCard
import com.developer.ted.composetutorial.model.Message

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Preview", "Android Jetpack Compose"))
        }
    }
}