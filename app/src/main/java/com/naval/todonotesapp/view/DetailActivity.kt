package com.naval.todonotesapp.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.naval.todonotesapp.R
import com.naval.todonotesapp.utils.AppConstant.DESCRIPTION
import com.naval.todonotesapp.utils.AppConstant.TAG
import com.naval.todonotesapp.utils.AppConstant.TITLE

class DetailActivity : AppCompatActivity() {

    lateinit var detailTextViewTitle :TextView
    lateinit var detailTextViewDescription : TextView

    override fun  onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindView()
        val intent = intent
        Log.d(TAG,intent.getStringExtra(TITLE))
        detailTextViewTitle.text = intent.getStringExtra(TITLE)
        detailTextViewDescription.text = intent.getStringExtra(DESCRIPTION)

    }

    private fun bindView() {

        detailTextViewTitle = findViewById(R.id.detailTextViewTitle)
        detailTextViewDescription = findViewById(R.id.detailTextViewDescription)
    }
}