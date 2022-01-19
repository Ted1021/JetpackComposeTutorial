package com.developer.ted.composetutorial

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.developer.ted.composetutorial.compose.ContactList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ContactList(
                    itemClickListener = { onItemClicked(it) },
                    phoneClickListener = { onPhoneClicked(it) }
                )
            }
        }
    }

    fun onItemClicked(name: String) {
        Toast.makeText(this.baseContext, name, Toast.LENGTH_SHORT).show()
    }

    fun onPhoneClicked(number: String) {
        Toast.makeText(this.baseContext, number, Toast.LENGTH_SHORT).show()
    }
}