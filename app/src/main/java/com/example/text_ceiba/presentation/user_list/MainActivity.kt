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

        showAlertDialog()
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        editTextSearch.doOnTextChanged { text, _, _, _ ->
                    _handleWord(text.toString())
        }
        GlobalScope.launch {
            loadUsers()
        }
    }

    fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cargando").setCancelable(false)
        alertDialogLoading = builder.create()
        alertDialogLoading.show()
    }


    fun _handleWord(word: String) {
        val filteredUsers = users?.filter { it.name.lowercase().contains(word.lowercase()) } ?: emptyList()
        //hide empty
        findViewById<RelativeLayout>(R.id.emptyView).visibility = if (filteredUsers.size > 0)  View.GONE else View.VISIBLE
        val recyclerViewSearchResults =
            findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewSearchResults)
        recyclerViewSearchResults.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerViewSearchResults.adapter = UserListItemAdapter(filteredUsers.toMutableList())
    }
    suspend fun loadUsers() {
        val usersApi = UserRepositoryApi().getUsers(context = this@MainActivity)
        if(loading) alertDialogLoading.dismiss()
        this@MainActivity!!.runOnUiThread {
            //Cambiar controles
            users = usersApi
            _handleWord("")
        }
    }
}