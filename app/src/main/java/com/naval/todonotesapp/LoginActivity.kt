package com.naval.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.naval.todonotesapp.PrefConstant.FULL_NAME
import com.naval.todonotesapp.PrefConstant.LOGGED_IN_STATUS
import com.naval.todonotesapp.PrefConstant.SHARED_PREF_NAME

class LoginActivity : AppCompatActivity() {

    lateinit var editTextFullName : EditText
    lateinit var editTextUserName : EditText
    lateinit var buttonLogin : Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    protected override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2);

        bindView()

        setupSharedPreferences()

        val clickAction = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()
                if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(userName)) {
                    var intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(FULL_NAME, fullName)
                    startActivity(intent)
                    saveLoginStatus()
                    saveFullName(fullName)
                } else Toast.makeText(this@LoginActivity, "Please enter all details", Toast.LENGTH_SHORT)

            }
        }

        buttonLogin.setOnClickListener(clickAction)

    }





    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(FULL_NAME, fullName)
        editor.apply()

    }

    private fun saveLoginStatus() {
        val status = true
        editor = sharedPreferences.edit()
        editor.putBoolean(LOGGED_IN_STATUS,true )
        editor.apply()
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun bindView() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName = findViewById(R.id.editTextUserName)
        buttonLogin = findViewById(R.id.materialButton)
    }

}