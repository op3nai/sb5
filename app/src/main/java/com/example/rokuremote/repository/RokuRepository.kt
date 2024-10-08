package com.example.rokuremote.repository

import com.example.rokuremote.model.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.*
import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request

class RokuRepository {
    private val client = OkHttpClient()

    suspend fun discoverDevices(): List<Device> {
        // ... (previous SSDP discovery code remains)
    }

    suspend fun connectToDevice(device: Device): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(Request.Builder()
                .url("http://${device.ip}:8060/query/device-info")
                .build()).execute()
            response.isSuccessful
        } catch (e: IOException) {
            false
        }
    }

    suspend fun sendCommand(command: String, device: Device) = withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(Request.Builder()
                .url("http://${device.ip}:8060/keypress/$command")
                .post(okhttp3.RequestBody.create(null, ByteArray(0)))
                .build()).execute()
            response.isSuccessful
        } catch (e: IOException) {
            false
        }
    }

    suspend fun launchChannel(channelId: String, device: Device) = withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(Request.Builder()
                .url("http://${device.ip}:8060/launch/$channelId")
                .post(okhttp3.RequestBody.create(null, ByteArray(0)))
                .build()).execute()
            response.isSuccessful
        } catch (e: IOException) {
            false
        }
    }
}