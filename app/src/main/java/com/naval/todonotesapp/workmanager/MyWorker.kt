package com.naval.todonotesapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.naval.todonotesapp.NotesApp

class MyWorker(var context: Context,var workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    override fun doWork(): Result {
        val notesApp = applicationContext as NotesApp
        val notesDao = notesApp.getNotesDb().notesDao()
        notesDao.deleteNotesWhenCompleted(true)
        return Result.success()
    }
}