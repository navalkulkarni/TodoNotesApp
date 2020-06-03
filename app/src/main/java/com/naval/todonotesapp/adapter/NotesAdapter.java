package com.naval.todonotesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naval.todonotesapp.R;
import com.naval.todonotesapp.clicklisteners.ItemClickListener;
import com.naval.todonotesapp.model.Notes;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> notesList;
    private ItemClickListener itemClickListener;

    public NotesAdapter(List<Notes> list,ItemClickListener listener){
        this.itemClickListener = listener;
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
        final Notes notes = notesList.get(position);

        holder.adapterTextViewTitle.setText(notes.getTitle());
        holder.adapterTextViewDescription.setText(notes.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(notes);
            }
        });
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
