package com.naval.todonotesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.naval.todonotesapp.R

class BlogActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindView()
    }

    private fun bindView() {
        recyclerView = findViewById(R.id.recyclerViewBlogs)
    }
}