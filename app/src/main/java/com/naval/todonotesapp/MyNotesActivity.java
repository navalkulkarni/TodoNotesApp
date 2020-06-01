package com.naval.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyNotesActivity extends AppCompatActivity {

    FloatingActionButton fabAddNotes;
    TextView textViewMyNotesTitle,textViewMyNotesDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        textViewMyNotesTitle = findViewById(R.id.textViewMyNotesTitle);
        textViewMyNotesDescription = findViewById(R.id.textViewMyNotesDescription);
        fabAddNotes = findViewById(R.id.fabAddNotes);

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("MyNotesButton","on click performed");
                setupDialog();
            }
        });

        Intent intent = getIntent();

        String fullName =  intent.getStringExtra("full_name");

        getSupportActionBar().setTitle(fullName);
        //Log.d("IntentDataPass",intent.getStringExtra("full_name"));
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
                textViewMyNotesTitle.setText(editTextTitle.getText().toString());
                textViewMyNotesDescription.setText(editTextDescription.getText().toString());
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }
}