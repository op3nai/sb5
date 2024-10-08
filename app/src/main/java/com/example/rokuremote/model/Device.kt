package com.example.rokuremote.model

data class Device(
    val id: String,
    val name: String,
    val ip: String,
    val isConnected: Boolean
)