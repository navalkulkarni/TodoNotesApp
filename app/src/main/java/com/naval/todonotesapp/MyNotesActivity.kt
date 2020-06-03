package com.naval.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naval.todonotesapp.AppConstant.DESCRIPTION
import com.naval.todonotesapp.AppConstant.TITLE
import com.naval.todonotesapp.PrefConstant.FULL_NAME
import com.naval.todonotesapp.PrefConstant.SHARED_PREF_NAME
import com.naval.todonotesapp.adapter.NotesAdapter
import com.naval.todonotesapp.clicklisteners.ItemClickListener
import com.naval.todonotesapp.model.Notes
import java.util.*

class MyNotesActivity : AppCompatActivity() {
    var fabAddNotes: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    var fullName: String? = null
    var sharedPreferences: SharedPreferences? = null
    var list = ArrayList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        setupSharedPreference()
        bindView()
        intentData
        fabAddNotes!!.setOnClickListener { setupDialog() }
        supportActionBar!!.title = fullName
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private val intentData: Unit
        private get() {
            val intent = intent
            fullName = intent.getStringExtra(AppConstant.FULL_NAME)
            if (TextUtils.isEmpty(fullName)) {
                fullName = sharedPreferences!!.getString(FULL_NAME, "")
            }
        }

    private fun bindView() {
        fabAddNotes = findViewById(R.id.fabAddNotes)
        recyclerView = findViewById(R.id.recyclerViewNotes)
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.add_my_notes_dialog_layout, null)
        val editTextTitle: EditText
        val editTextDescription: EditText
        val buttonSubmit: Button
        editTextTitle = view.findViewById(R.id.editTextDialogTitle)
        editTextDescription = view.findViewById(R.id.editTextDialogDescription)
        buttonSubmit = view.findViewById(R.id.buttonSubmitDialog)
        val alertDialog = AlertDialog.Builder(this).setView(view).setCancelable(false).create()
        buttonSubmit.setOnClickListener {
            val t = editTextTitle.text.toString()
            val desc = editTextDescription.text.toString()
            if (!TextUtils.isEmpty(t) && !TextUtils.isEmpty(desc))
                list.add(Notes(t, desc))
            else
                Toast.makeText(this, "Can't Create an Empty Note", Toast.LENGTH_SHORT).show()
            setupRecyclerView()
            alertDialog.hide()
        }
        alertDialog.show()
    }

    private fun setupRecyclerView() {
        val listener: ItemClickListener = object : ItemClickListener {
            override fun onClick(note: Notes) {
                val intent :Intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(TITLE, note.title)
                intent.putExtra(DESCRIPTION, note.description)
                startActivity(intent)
            }
        }
        val adapter = NotesAdapter(list, listener)
        val manager = LinearLayoutManager(this@MyNotesActivity)
        manager.orientation = RecyclerView.VERTICAL
        recyclerView!!.layoutManager = manager
        recyclerView!!.adapter = adapter
    }
}