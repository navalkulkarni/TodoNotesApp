package com.naval.todonotesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naval.todonotesapp.MyNotesActivity;
import com.naval.todonotesapp.R;
import com.naval.todonotesapp.model.Notes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    List<Notes> notesList;

    public NotesAdapter(List<Notes> list){
        this.notesList = list;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes notes = notesList.get(position);

        holder.adapterTextViewTitle.setText(notes.getTitle());
        holder.adapterTextViewDescription.setText(notes.getDescription());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView adapterTextViewTitle,adapterTextViewDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            adapterTextViewTitle = itemView.findViewById(R.id.adapterTextViewTitle);
            adapterTextViewDescription = itemView.findViewById(R.id.adapterTextViewDescription);
        }
    }
}
