package com.naval.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.naval.todonotesapp.PrefConstant.FULL_NAME;
import static com.naval.todonotesapp.PrefConstant.LOGGED_IN_STATUS;

public class LoginActivity extends AppCompatActivity {

    EditText editTextFullName,editTextUserName;
    Button buttonLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonLogin = findViewById(R.id.materialButton);

        setupSharedPreferences();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Click","On Click Performed");
                String fullName = editTextFullName.getText().toString();
                String userName = editTextUserName.getText().toString();

                if(!TextUtils.isEmpty(fullName)&&!TextUtils.isEmpty(userName))
                {
                    Intent intent = new Intent(LoginActivity.this,MyNotesActivity.class);
                    intent.putExtra(AppConstant.FULL_NAME,fullName);
                    startActivity(intent);
                    saveLoginStatus();
                    saveFullName(fullName);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Full Name and User Name can't be empty",Toast.LENGTH_SHORT).show();
                }

            }
        };

        buttonLogin.setOnClickListener(listener);
    }

    private void saveFullName(String fullName) {
        editor = sharedPreferences.edit();
        editor.putString(FULL_NAME,fullName);
        editor.apply();
    }

    private void saveLoginStatus() {
        editor = sharedPreferences.edit();
        editor.putBoolean(LOGGED_IN_STATUS,true);
        editor.apply();
    }

    private void setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREF_NAME,MODE_PRIVATE);
    }
}