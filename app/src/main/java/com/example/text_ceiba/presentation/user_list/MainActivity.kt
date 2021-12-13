package com.example.text_ceiba.presentation.user_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.text_ceiba.R
import com.example.text_ceiba.data.repositories.user.UserRepositoryApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val test = UserRepositoryApi().getUsers();
            Log.v(
                "main", "test: ${test.size}"
            )
        }
    }
}