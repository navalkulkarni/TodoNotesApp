package com.naval.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naval.todonotesapp.adapter.NotesAdapter;
import com.naval.todonotesapp.clicklisteners.ItemClickListener;
import com.naval.todonotesapp.model.Notes;

import java.util.ArrayList;

import static com.naval.todonotesapp.AppConstant.DESCRIPTION;
import static com.naval.todonotesapp.AppConstant.TITLE;
import static com.naval.todonotesapp.PrefConstant.FULL_NAME;
import static com.naval.todonotesapp.PrefConstant.SHARED_PREF_NAME;

public class MyNotesActivity extends AppCompatActivity {

    FloatingActionButton fabAddNotes;
    RecyclerView recyclerView;

    String fullName;
    SharedPreferences sharedPreferences;
    ArrayList<Notes> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        setupSharedPreference();

        bindView();

        getIntentData();

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupDialog();
            }
        });

        getSupportActionBar().setTitle(fullName);

    }

    private void setupSharedPreference() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        fullName =  intent.getStringExtra(AppConstant.FULL_NAME);
        if(TextUtils.isEmpty(fullName))
        {
            fullName = sharedPreferences.getString(FULL_NAME,"");
        }
    }

    private void bindView() {

        fabAddNotes = findViewById(R.id.fabAddNotes);
        recyclerView = findViewById(R.id.recyclerViewNotes);
    }

    private void setupDialog() {
        View view = LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_my_notes_dialog_layout,null);
        final EditText editTextTitle,editTextDescription;
        Button buttonSubmit;

        editTextTitle = view.findViewById(R.id.editTextDialogTitle);
        editTextDescription = view.findViewById(R.id.editTextDialogDescription);
        buttonSubmit = view.findViewById(R.id.buttonSubmitDialog);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).setCancelable(false).create();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = editTextTitle.getText().toString();
                String desc = editTextDescription.getText().toString();

                if(!TextUtils.isEmpty(t)&&!TextUtils.isEmpty(desc))
                    list.add(new Notes(t,desc));
                else
                    Toast.makeText(MyNotesActivity.this,"Can't Create an Empty Note",Toast.LENGTH_SHORT).show();

                setupRecyclerView();

                alertDialog.hide();
            }
        });

        alertDialog.show();
    }

    private void setupRecyclerView() {
        ItemClickListener listener = new ItemClickListener() {
            @Override
            public void onClick(Notes note) {
                Intent intent = new Intent(MyNotesActivity.this,DetailActivity.class);
                intent.putExtra(TITLE,note.getTitle());
                intent.putExtra(DESCRIPTION,note.getDescription());
                startActivity(intent);
            }
        };

        NotesAdapter adapter = new NotesAdapter(list,listener);
        LinearLayoutManager manager = new LinearLayoutManager(MyNotesActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}