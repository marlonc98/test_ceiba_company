package com.example.text_ceiba.data.repositories.common

import android.content.Context
import android.util.Log
import com.example.text_ceiba.common.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody


class HttpApi {
    val client = OkHttpClient()
    val BASE_URL = Constants.kBASE_URL
    public suspend fun get(relativeUrl: String): ResponseBody? {
        val relativeUrl = BASE_URL + relativeUrl

        val request: Request = Request.Builder()
            .url(relativeUrl)
            .build()
        try {
            val response: Response = client.newCall(request).execute()
            return response.body!!
        }catch (e: Exception){
            return null
        }
    }
}