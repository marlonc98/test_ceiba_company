package com.example.text_ceiba.presentation.user_posts_list

import PostListItemAdapter
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.text_ceiba.R
import com.example.text_ceiba.domain.model.Post
import com.example.text_ceiba.domain.model.User
import com.example.text_ceiba.domain.use_cases.post.SearchPostByUserUserCase
import com.example.text_ceiba.domain.use_cases.user.GetUserUserCase
import com.example.text_ceiba.presentation.common.loading_alert.ShowDialogs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostActivity : AppCompatActivity() {
    lateinit var alertDialogLoading: AlertDialog
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        //show loading
        alertDialogLoading = ShowDialogs.showLoadingDialog(this)

        //add listeners
        addListeners()

        //get UserId from intent
        val userId = intent.getIntExtra("userId", 0)
        GlobalScope.launch {
            loadUser(userId)
            loadPosts(userId)
        }
    }

    private fun addListeners() {
    }

    private suspend fun loadUser(userId: Int) {
        user = GetUserUserCase(this).execute(userId)
    }
    private suspend fun loadPosts(userId: Int) {
        //load data
        val posts = SearchPostByUserUserCase(this).execute(userId)

        //adapt data
        this@PostActivity!!.runOnUiThread {

          findViewById<RelativeLayout>(R.id.emptyView).visibility = if (posts.size > 0)  View.GONE else View.VISIBLE
            val recyclerViewSearchResults =
                findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewPostsResults)
            recyclerViewSearchResults.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this)
            recyclerViewSearchResults.adapter = PostListItemAdapter(posts.toMutableList())
        }

        alertDialogLoading.dismiss()
    }
}