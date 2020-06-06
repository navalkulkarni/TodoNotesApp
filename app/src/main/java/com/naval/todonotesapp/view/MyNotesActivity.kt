package com.naval.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naval.todonotesapp.NotesApp
import com.naval.todonotesapp.R
import com.naval.todonotesapp.utils.AppConstant.DESCRIPTION
import com.naval.todonotesapp.utils.AppConstant.TITLE
import com.naval.todonotesapp.utils.PrefConstant.FULL_NAME
import com.naval.todonotesapp.utils.PrefConstant.SHARED_PREF_NAME
import com.naval.todonotesapp.adapter.NotesAdapter
import com.naval.todonotesapp.clicklisteners.ItemClickListener
import com.naval.todonotesapp.db.Notes

import com.naval.todonotesapp.utils.AppConstant
import com.naval.todonotesapp.utils.AppConstant.IMAGEPATH
import com.naval.todonotesapp.workmanager.MyWorker
import java.util.*
import java.util.concurrent.TimeUnit

class MyNotesActivity : AppCompatActivity() {

    val ADD_NOTES_CODE = 100
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
        setupClickListeners()
        setActionBarText()
        intentData
        getNotesFromDb()
        setupRecyclerView()
        setupWorkManager()
    }

    private fun setupWorkManager() {
        val constraint = Constraints.Builder().build()
        val request = PeriodicWorkRequest.
                      Builder(MyWorker::class.java,15,TimeUnit.MINUTES).
                      setConstraints(constraint).
                      build()
        WorkManager.getInstance().enqueue(request)
    }

    private fun setActionBarText() {
        supportActionBar!!.title = fullName
    }

    private fun setupClickListeners() {
        fabAddNotes?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                //setupDialog()
                val intent = Intent(this@MyNotesActivity,AddNotesActivity::class.java)
                startActivityForResult(intent,ADD_NOTES_CODE)
            }
        })
    }

    private fun getNotesFromDb() {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()

        list.addAll(notesDao.getAll())
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
            if (!TextUtils.isEmpty(t) && !TextUtils.isEmpty(desc)){
                list.add(Notes(title = t,description =  desc))
                addNotestoDb(Notes(title = t,description = desc))
            }
            else
                Toast.makeText(this, "Can't Create an Empty Note", Toast.LENGTH_SHORT).show()

            alertDialog.hide()
        }
        alertDialog.show()
    }

    private fun addNotestoDb(note: Notes) {
        val notesApp:NotesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.insertNote(note)
    }

    private fun setupRecyclerView() {
        val listener: ItemClickListener = object : ItemClickListener {
            override fun onClick(note: Notes) {
                val intent :Intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(TITLE, note.title)
                intent.putExtra(DESCRIPTION, note.description)
                startActivity(intent)
            }

            override fun onUpdate(note: Notes) {
                val notesApp = applicationContext as NotesApp
                val notesDao = notesApp.getNotesDb().notesDao()
                notesDao.updateNote(note)
            }
        }
        val adapter = NotesAdapter(list, listener)
        val manager = LinearLayoutManager(this@MyNotesActivity)
        manager.orientation = RecyclerView.VERTICAL
        recyclerView!!.layoutManager = manager
        recyclerView!!.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTES_CODE){
            val title = data?.getStringExtra(TITLE)
            val description = data?.getStringExtra(DESCRIPTION)
            val imagePath = data?.getStringExtra(IMAGEPATH)

            val notes = Notes(title = title!!,description = description!!,imagePath = imagePath!!,isTaskCompleted = false)
            addNotestoDb(notes)
            list.add(notes)
            recyclerView?.adapter?.notifyItemChanged(list.size-1)
        }
    }
}