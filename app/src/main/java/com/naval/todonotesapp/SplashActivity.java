package com.naval.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import static com.naval.todonotesapp.PrefConstant.SHARED_PREF_NAME;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSharedPreference();
        checkLoginStatus();
    }

    private void setupSharedPreference() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean(PrefConstant.LOGGED_IN_STATUS,false);
        if(isLoggedIn)
        {
            Intent intent = new Intent(SplashActivity.this,MyNotesActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}