package com.naval.todonotesapp

import android.app.Application
import com.naval.todonotesapp.db.NotesDatabase

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}