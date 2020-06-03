package com.naval.todonotesapp.clicklisteners

import com.naval.todonotesapp.model.Notes

interface ItemClickListener {
    fun onClick(notes: Notes);
}