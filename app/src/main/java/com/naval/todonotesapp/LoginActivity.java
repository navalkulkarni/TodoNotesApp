package com.naval.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextFullName,editTextUserName;
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonLogin = findViewById(R.id.materialButton);

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
                }
                else{
                    Toast.makeText(LoginActivity.this,"Full Name and User Name can't be empty",Toast.LENGTH_SHORT).show();
                }

            }
        };

        buttonLogin.setOnClickListener(listener);
    }
}