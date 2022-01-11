package com.developer.ted.composetutorial.model

data class Message(
    val author: String,
    val body: String,
    val me: Boolean = true
)
