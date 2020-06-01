package com.naval.todonotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.naval.todonotesapp.PrefConstant.FULL_NAME;
import static com.naval.todonotesapp.PrefConstant.SHARED_PREF_NAME;

public class MyNotesActivity extends AppCompatActivity {

    FloatingActionButton fabAddNotes;
    TextView textViewMyNotesTitle,textViewMyNotesDescription;
    String fullName;
    SharedPreferences sharedPreferences;

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
        textViewMyNotesTitle = findViewById(R.id.textViewMyNotesTitle);
        textViewMyNotesDescription = findViewById(R.id.textViewMyNotesDescription);
        fabAddNotes = findViewById(R.id.fabAddNotes);
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