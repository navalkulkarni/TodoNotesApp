package com.naval.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.naval.todonotesapp.R
import com.naval.todonotesapp.onboarding.OnboardingActivity
import com.naval.todonotesapp.utils.PrefConstant

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    val TAG = "FirebaseToken"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
        getFirebaseToken()
    }

    private fun getFirebaseToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast

                    //Log.d(TAG, token)
                    //Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreferences.getBoolean(PrefConstant.LOGGED_IN_STATUS, false)
        val isOnBoarded:Boolean  = sharedPreferences.getBoolean(PrefConstant.ONBOARDING,false)
        if (isLoggedIn) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            if(isOnBoarded){
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@SplashActivity, OnboardingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}