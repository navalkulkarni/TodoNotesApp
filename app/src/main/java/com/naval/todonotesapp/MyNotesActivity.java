package com.naval.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        Intent intent = getIntent();

        String fullName =  intent.getStringExtra("full_name");

        getSupportActionBar().setTitle(fullName);
        Log.d("IntentDataPass",intent.getStringExtra("full_name"));
    }
}