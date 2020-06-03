package com.naval.todonotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naval.todonotesapp.R
import com.naval.todonotesapp.clicklisteners.ItemClickListener
import com.naval.todonotesapp.model.Notes

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private val notesList: List<Notes>
    private val itemClickListener: ItemClickListener

    constructor(notesList: List<Notes>, itemClickListener: ItemClickListener) : super() {
        this.notesList = notesList
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                            inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notes = notesList[position]
        holder.adapterTextViewTitle.text = notes.title
        holder.adapterTextViewDescription.text = notes.description
        holder.itemView.setOnClickListener { itemClickListener.onClick(notes) }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var adapterTextViewTitle: TextView
        var adapterTextViewDescription: TextView

        init {
            adapterTextViewTitle = itemView.findViewById(R.id.adapterTextViewTitle)
            adapterTextViewDescription = itemView.findViewById(R.id.adapterTextViewDescription)
        }
    }

}