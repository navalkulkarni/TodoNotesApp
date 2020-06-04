package com.naval.todonotesapp.clicklisteners

import com.naval.todonotesapp.db.Notes


interface ItemClickListener {
    fun onClick(note: Notes);
    fun onUpdate(note:Notes);
}