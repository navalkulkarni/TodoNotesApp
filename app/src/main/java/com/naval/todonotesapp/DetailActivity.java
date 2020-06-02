package com.naval.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.naval.todonotesapp.AppConstant.DESCRIPTION;
import static com.naval.todonotesapp.AppConstant.TITLE;

public class DetailActivity extends AppCompatActivity {

    TextView detailTextViewTitle,detailTextViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindView();
        Intent intent = getIntent();
        detailTextViewTitle.setText(intent.getStringExtra(TITLE));
        detailTextViewDescription.setText(intent.getStringExtra(DESCRIPTION));
    }

    private void bindView() {
        detailTextViewTitle = findViewById(R.id.detailTextViewTitle);
        detailTextViewDescription = findViewById(R.id.detailTextViewDescription);
    }
}