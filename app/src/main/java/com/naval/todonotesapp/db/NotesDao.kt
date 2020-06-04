package com.naval.todonotesapp.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NotesDao {

    @Query("SELECT * FROM notesData")
    fun getAll():List<Notes>

    @Insert(onConflict = REPLACE)
    fun insertNote(note:Notes)

    @Update
    fun updateNote(note:Notes)

    @Delete
    fun deleteNote(note:Notes)
}