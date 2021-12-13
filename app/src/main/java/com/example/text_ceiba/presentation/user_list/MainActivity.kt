package com.example.text_ceiba.presentation.user_list

import UserListItemAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.text_ceiba.R
import com.example.text_ceiba.data.repositories.user.UserRepositoryApi
import com.example.text_ceiba.domain.model.User
import com.example.text_ceiba.presentation.common.loading_alert.ShowDialogs
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var alertDialogLoading:AlertDialog
    var loading:Boolean = true
    var users: List<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //show loading
        alertDialogLoading = ShowDialogs.showLoadingDialog(this)

        //add listeners
        addListeners()

        //necessary for show suspe nd fun
        GlobalScope.launch {
            loadUsers()
        }
    }

    private fun addListeners(){
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        editTextSearch.doOnTextChanged { text, _, _, _ ->
            handleWord(text.toString())
        }
    }


    private fun handleWord(word: String) {
        val filteredUsers = users?.filter { it.name.lowercase().contains(word.lowercase()) } ?: emptyList()
        //hide empty
        findViewById<RelativeLayout>(R.id.emptyView).visibility = if (filteredUsers.size > 0)  View.GONE else View.VISIBLE
        val recyclerViewSearchResults =
            findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewSearchResults)
        recyclerViewSearchResults.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewSearchResults.adapter = UserListItemAdapter(filteredUsers.toMutableList())
    }

    private suspend fun loadUsers() {
        val usersApi = UserRepositoryApi().getUsers(context = this@MainActivity)
        if(loading) alertDialogLoading.dismiss()
        this@MainActivity!!.runOnUiThread {
            //Cambiar controles
            users = usersApi
            handleWord("")
        }
    }
}